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
            // ���յ����󣬼�¼��������
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            // ��¼����������
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
     * ��װ����ͷ��Ϣ��ֻ��ӡ������Ҫ��ӡ�ģ�
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
     * ��װ���������Ϣ
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

        //��ȡbody��Ϣ
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
        //��ȡ�����صķ���
        Method method = signature.getMethod();
        //��ȡ�����صķ�����
        String methodName = method.getName();
        LOGGER.info("start��{}", methodName);
        if (method.getAnnotation(ReSubmit.class) != null) {
            Object obj = doReSubmit(proceedingJoinPoint);
            long costMs = System.currentTimeMillis() - beginTime;
            LOGGER.info("end��{} consuming��{}ms", methodName, costMs);
            return obj;
        }else {
            try {
                Object obj = proceedingJoinPoint.proceed();
                long costMs = System.currentTimeMillis() - beginTime;
                LOGGER.info("end��{} consuming��{}ms", methodName, costMs);
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
        // �˴�������token����JSessionId
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
            // ��ȡ���ɹ�
            Object result;
            try {
                // ִ�н���
                result = proceedingJoinPoint.proceed();
            } finally {
                // ����
                redisLockService.unLock(key, String.valueOf(currentTime));
                LOGGER.info("releaseLock success, key = [{}], clientId = [{}]", key, String.valueOf(currentTime));
            }

            return result;

        } else {
            // ��ȡ��ʧ�ܣ���Ϊ���ظ��ύ������
            LOGGER.info("tryLock fail, key = [{}]", key);
            Map<String, Object> map = new HashMap<>();
            map.put("code", GenericResponse.CODE_NG);
            map.put("msg","��������Ƶ�������Ժ�����");
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
     * ������
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