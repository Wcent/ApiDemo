package org.cent.ApiDemo.exception;

/**
 * @author Vincent
 * @version 1.0 2019/6/22
 */
public class CommonException extends RuntimeException {

    private String code;

    public CommonException(String code, String message) {
        super(String.format("%s - %s", code, message));
        this.code = code;
    }

    public CommonException(String code, String message, Throwable throwable) {
        super(String.format("%s - %s, %s", code, message, throwable.getMessage()));
        this.code =  code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
