package org.cent.ApiDemo.constant;

/**
 * 统一异常枚举
 * @author Vincent
 * @version 1.0 2019/6/29
 */
public enum  ExceptionEnum {

    REQ0000("REQ0000", "请求异常"),
    REQ0001("REQ0001", "请求参数类型不匹配：%s"),
    REQ0002("REQ0002", "请求参数缺失：%s"),
    REQ0003("REQ0003", "请求报文体空"),
    REQ0004("REQ0004", "请求报文体JSON解析异常"),
    REQ0005("REQ0005", "请求报文无效：%s"),
    REQ0006("REQ0006", "请求报文缺失"),
    REQ0007("REQ0007", "接口数据[%s]缺失"),
    REQ0008("REQ0008", "请求路径[%s]不存在"),
    REQ0009("REQ0009", "请求路径不存在：%s"),

    SRV0000("SRV0000", "服务异常：%s"),

    OTH0000("OTH0000", "其他异常：%s"),
    OTH0001("OTH0001", "报文类型无效，仅支持application/json"),
    OTH0002("OTH0002", "报文编码无效，仅支持utf-8"),
    OTH0003("OTH0003", "拦截无效请求"),

    UNK0000("UNK0000", "未知异常：%s"),
    UNK0001("UNK0001", "未知异常，响应码：%d"),
    UNK0002("UNK0002", "过滤器跳转未知异常");

    private String code;
    private String message;

    private ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", code, message);
    }
}
