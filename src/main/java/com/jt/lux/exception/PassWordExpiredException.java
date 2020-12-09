package com.jt.lux.exception;


public class PassWordExpiredException extends RuntimeException {

    public PassWordExpiredException(String message, Throwable cause) {
        super(message,cause);
    }

    public PassWordExpiredException(String message) {
        super(message);
    }
}
