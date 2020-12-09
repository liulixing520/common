package com.jt.lux.exception;


public class ParamException extends RuntimeException  {
    public ParamException(String message, Throwable cause) {
        super(message,cause);
    }
    public ParamException(String message) {
        super(message);
    }
}
