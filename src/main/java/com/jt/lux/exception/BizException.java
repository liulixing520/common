package com.jt.lux.exception;

/**
* 描述：业务操作异常类
* 类名称：BizException.java
* 版本：1.0
* 创建日期： 2018/8/3 9:28
* 版权：
*/
public class BizException extends RuntimeException {
    public BizException(String message, Throwable cause) {
        super(message,cause);
    }

    public BizException(String message) {
        super(message);
    }
}
