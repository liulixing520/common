package com.jt.lux.exception;

/**
 * @author yuweifeng
 * @description：密码过期异常类
 * @params
 * @return
 * @time 2020/4/30
 */
public class PassWordExpiredException extends RuntimeException {

    public PassWordExpiredException(String message, Throwable cause) {
        super(message,cause);
    }

    public PassWordExpiredException(String message) {
        super(message);
    }
}
