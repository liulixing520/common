package com.jt.lux.aop;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.lux.annotations.ReSubmit;
import com.jt.lux.config.AuthProperties;
import com.jt.lux.service.redis.RedisLockService;
import com.jt.lux.util.GenericResponse;
import org.apache.ibatis.reflection.ArrayUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Aspect
@Component
@Order(2)
public class HttpAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);


    private static final long TIME = 10 * 1000;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisLockService redisLockService;


    private final String userPrefix = "USER:RESUB-TOKEN:";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthProperties authConf;


    @Pointcut("execution(public * com.jt.lux.controller.*.*.*(..))")
    public void log(){

    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
            String url = request.getRequestURL().toString();
            String classMethod = joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName();
            Signature signature = joinPoint.getSignature();
            StringBuilder sb = new StringBuilder();
            sb.append("URL -> ").append(url).append(" | ").append("CLASS_METHOD -> ").append(classMethod);
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            String headers = buildRequestHeaders(request);

            if (!StringUtil.isEmpty(headers)) {
                sb.append(" | ").append("HEADER -> ").append(headers);
            }
            String params = buildRequestParams(request);
            if (!StringUtil.isEmpty(params)) {
                sb =new StringBuilder().append("PARAMS -> ").append(params);
            }
            LOGGER.info(sb.toString());
        } catch (Throwable ex) {
            LOGGER.error("HaLogParamAspect error.", ex);
        }
    }

    /**
     * 组装请求头信息（只打印配置需要打印的）
     *
     */
    private String buildRequestHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            while (headerNames.hasMoreElements()) {
                String nextElement = headerNames.nextElement();
                if(nextElement.equals("Authorization")){
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(nextElement).append("=").append(request.getHeader(nextElement));
            }
            return sb.toString();
        }
    }

    /**
     * 组装请求参数信息
     */
    private String buildRequestParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (null == parameterMap) {
            return "";
        }else if (0 == parameterMap.size()) {
            return "";
        } else {
            for (String key : parameterMap.keySet()) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(key).append("=").append(ArrayUtil.toString(parameterMap.get(key)));
            }

        }

        //获取body信息
        BufferedReader br = null;
        try
        {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取被拦截的方法
        Method method = signature.getMethod();
        //获取被拦截的方法名
        String methodName = method.getName();
        LOGGER.info("start，{}", methodName);
        if (method.getAnnotation(ReSubmit.class) != null) {
            Object obj = doReSubmit(proceedingJoinPoint);
            long costMs = System.currentTimeMillis() - beginTime;
            LOGGER.info("end，{} consuming：{}ms", methodName, costMs);
            return obj;
        }else {
            try {
                Object obj = proceedingJoinPoint.proceed();
                long costMs = System.currentTimeMillis() - beginTime;
                LOGGER.info("end，{} consuming：{}ms", methodName, costMs);
                return obj;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                throw throwable;
            }
        }

    }

    private Object doReSubmit(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        // 此处可以用token或者JSessionId
        String authorization = request.getHeader("Authorization");
        String[] parts = authorization.split("\\.");
        String token = userPrefix + parts[2];
        String path = request.getServletPath();
        String key = getKey(token, path);
        long currentTime = System.currentTimeMillis() + TIME;
        boolean isSuccess = redisLockService.lock(key, String.valueOf(currentTime));
        LOGGER.info("tryLock key = [{}], currentTime = [{}]", key, String.valueOf(currentTime));
        if (isSuccess) {
            LOGGER.info("tryLock success, key = [{}], currentTime = [{}]", key, String.valueOf(currentTime));
            // 获取锁成功
            Object result;
            try {
                // 执行进程
                result = proceedingJoinPoint.proceed();
            } finally {
                // 解锁
                redisLockService.unLock(key, String.valueOf(currentTime));
                LOGGER.info("releaseLock success, key = [{}], clientId = [{}]", key, String.valueOf(currentTime));
            }

            return result;

        } else {
            // 获取锁失败，认为是重复提交的请求
            LOGGER.info("tryLock fail, key = [{}]", key);
            Map<String, Object> map = new HashMap<>();
            map.put("code", GenericResponse.CODE_NG);
            map.put("msg","操作过于频繁，请稍后再试");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(map));
            return null;
        }
    }

    private String getKey(String token, String path) {
        return token + path;
    }

    private String getClientId() {
        return UUID.randomUUID().toString();
    }

    /**
     *
     * @param object
     * 描述：
     */
    @AfterReturning(pointcut = "log()",returning = "object")
    public void doAfterReturing(Object object){
        if(object instanceof JSONObject){
            LOGGER.info("RESPONSE -> {}", object.toString());
        }else if(object instanceof ResponseEntity){
            ResponseEntity res  = (ResponseEntity) object;
            LOGGER.info("RESPONSE -> {}", JSONObject.toJSON(res.getBody()).toString());
        }
    }
}