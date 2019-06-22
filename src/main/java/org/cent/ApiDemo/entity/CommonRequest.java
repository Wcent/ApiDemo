package org.cent.ApiDemo.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Map;

/**
 * 公共请求报文
 * @author cent
 * @version 1.0 2019年6月22日
 */
public class CommonRequest implements Serializable {

    private String version;
    private String encoding;
    private String signType;
    private String sign;
    private String requestFrom;
    private String timestamp;
    private Map<String, Object> body;

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

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
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
