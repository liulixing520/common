package com.jt.lux.exception;
/**
 * @Description: 服务调用异常类
 * @author: wephone
 * @Date: 2018/8/30 09:29
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message, Throwable cause) {
        super(message,cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
