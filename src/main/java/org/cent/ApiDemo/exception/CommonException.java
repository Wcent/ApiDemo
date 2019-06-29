package org.cent.ApiDemo.exception;

import org.cent.ApiDemo.constant.ExceptionEnum;

/**
 * 公共异常
 * @author Vincent
 * @version 1.0 2019/6/22
 */
public class CommonException extends RuntimeException {

    /**
     * 默认其他异常
     */
    public CommonException() {
        super(ExceptionEnum.OTH0000.toString());
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    protected CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
