package com.jt.lux.exception;


public class ReSubmitException extends RuntimeException {
    public ReSubmitException(String message, Throwable cause) {
        super(message,cause);
    }

    public ReSubmitException(String message) {
        super(message);
    }
}
