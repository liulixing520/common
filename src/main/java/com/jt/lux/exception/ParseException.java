package com.jt.lux.exception;

public class ParseException extends RuntimeException {

    public ParseException(String message, Throwable cause) {
        super(message,cause);
    }
    public ParseException(String message) {
        super(message);
    }
}
