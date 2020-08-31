package com.jt.lux.exception;


/**
 * @author yuweifeng
 * @description 重复提交异常类
 * @time 2020/4/2
 */
public class ReSubmitException extends RuntimeException {
    public ReSubmitException(String message, Throwable cause) {
        super(message,cause);
    }

    public ReSubmitException(String message) {
        super(message);
    }
}
