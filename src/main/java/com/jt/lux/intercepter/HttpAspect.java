package com.jt.lux.intercepter;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.reflection.ArrayUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

@Aspect
@Component
public class HttpAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);


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
            LOGGER.info(sb.toString());
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
        LOGGER.info("start，method：{}", methodName);
        try {
            Object obj = proceedingJoinPoint.proceed();
            long costMs = System.currentTimeMillis() - beginTime;
            LOGGER.info("{} end，consuming：{}ms", methodName, costMs);
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
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
        }else if(object instanceof com.alibaba.fastjson.JSONObject){
            LOGGER.info("RESPONSE -> {}", object.toString());
        }else if(object instanceof ResponseEntity){
            ResponseEntity res  = (ResponseEntity) object;
            LOGGER.info("RESPONSE -> {}", JSONObject.toJSON(res.getBody()).toString());
        }
    }
}