package com.jt.lux;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.lux.util.idg.DefaultUidGenerator;
import com.jt.lux.util.idg.WorkerIdAssigner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@EnableSwagger2
@EnableAsync
@MapperScan(basePackages = "com.jt.lux.mapper")
public class CommonApplication {

	private static final int WORKER_BITS = 22;
	private static final int TIME_BITS = 29; // 可运行大约17年
	private static final int SEQ_BITS = 63 - WORKER_BITS - TIME_BITS;

	@Value("${jtpf.idg.workerId}")
	private String workerId;

	@Value("${jtpf.idg.workerIdConf}")
	private String workerIdConf;


	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}

	@Bean("idg")
	public DefaultUidGenerator uidGenerator() {
		DefaultUidGenerator g = new DefaultUidGenerator();
		//g.setWorkerIdAssigner(zkWorkerIdAssigner());
		g.setWorkerIdAssigner(envWorkerIdAssigner(workerId));
		g.setSeqBits(SEQ_BITS);
		g.setTimeBits(TIME_BITS);
		g.setWorkerBits(WORKER_BITS);
		return g;
	}


	@Bean
	public WorkerIdAssigner envWorkerIdAssigner(String workerId) {
		Long wid = null;
		if ("hostname".equalsIgnoreCase(workerIdConf)) {
			try {
				String host = InetAddress.getLocalHost().getHostName();
				int pos = host.lastIndexOf("-");
				wid = Long.parseLong(host.substring(pos + 1));
			} catch (UnknownHostException uhe) {
				throw new RuntimeException(uhe);
			}
		} else {
			wid = Long.parseLong(workerId);
		}

		return new EnvWorkerIdAssigner(wid);
	}
	private static final class EnvWorkerIdAssigner implements WorkerIdAssigner {
		private long workerId;
		public EnvWorkerIdAssigner(long workerId) {
			this.workerId = workerId;
		}
		@Override
		public long assignWorkerId() {
			return this.workerId;
		}
	}

	@Bean(name = "restTemplate")
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);

		// 使用Jackson2JsonRedisSerialize 替换默认序列化
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

		// 设置value的序列化规则和 key的序列化规则
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

		redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setEnableDefaultSerializer(true);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
