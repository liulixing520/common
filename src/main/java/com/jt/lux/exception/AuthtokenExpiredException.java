package com.jt.lux.exception;


public class AuthtokenExpiredException extends Exception {

    public AuthtokenExpiredException() {
    }

    public AuthtokenExpiredException(String message) {
        super(message);
    }

    public AuthtokenExpiredException(Throwable cause) {
        super(cause);
    }

    public AuthtokenExpiredException(String message, Throwable cause) {
            super(message, cause);
        }
}
