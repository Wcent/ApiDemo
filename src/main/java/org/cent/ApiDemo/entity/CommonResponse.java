package org.cent.ApiDemo.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CommonResponse implements Serializable {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String UNKNOWN = "unknown";

    private String version;
    private String encoding;
    private String signType;
    private String sign;
    private String status;
    private String code;
    private String message;
    private String timestamp;
    private Map<String, Object> body;

    public CommonResponse() {
        version = "1.0";
        encoding = "utf-8";
        status = UNKNOWN;
        timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getSignType() {
        return signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
