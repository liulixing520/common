package com.jt.lux.exception;
/**
 * @Description: 
 * @author: wephone
 * @Date: 2018/9/3 19:22
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
public class ParseException extends RuntimeException {

    public ParseException(String message, Throwable cause) {
        super(message,cause);
    }
    public ParseException(String message) {
        super(message);
    }
}
