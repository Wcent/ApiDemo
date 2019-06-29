package org.cent.ApiDemo.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.cent.ApiDemo.constant.ExceptionEnum;
import org.cent.ApiDemo.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 公共请求报文
 * @author cent
 * @version 1.0 2019年6月22日
 */
public class CommonRequest implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonRequest.class);

    @NotBlank(message = "版本号不能为空")
    private String version;

    @NotBlank(message = "报文编码不能为空")
    @Pattern(regexp = "^[u|U][t|T][f|F]-{0,1}8$", message = "报文编码必须为utf-8")
    private String encoding;

    @NotBlank(message = "加签类型不能为空")
    private String signType;

    @NotBlank(message = "签名不能为空")
    private String sign;

    @NotBlank(message = "请求方不能为空")
    private String requestFrom;

    @NotBlank(message = "请求时间不能为空")
    private String timestamp;

    @NotNull(message = "请求体不能为空")
    private Map<String, Object> body;

    /**
     * 取传入单包接口数据
     * @param name 接口名称
     * @param clazz 反序列化实体类类型
     * @param <T> 实体类型
     * @return 反序列化实例
     */
    public <T> T getItem(String name, Class<T> clazz) {
        if (body == null) {
            throw new CommonException(ExceptionEnum.REQ0003.toString());
        }
        try {
            T item = JSON.parseObject(JSON.toJSONString(body.get(name)), clazz);
            if (item == null) {
                throw new CommonException(String.format(ExceptionEnum.REQ0007.toString(), name));
            }
            return item;
        } catch (JSONException ex) {
            LOGGER.error(ExceptionEnum.REQ0004.toString(), ex);
            throw new CommonException(ExceptionEnum.REQ0004.toString());
        }
    }

    /**
     * 取传入多包接口数据
     * @param name 接口名称
     * @param clazz 反序列化实体类类型
     * @param <T> 实体类型
     * @return 反序列化实例集合
     */
    public <T> List<T> getItems(String name, Class<T> clazz) {
        if (body == null) {
            throw new CommonException(ExceptionEnum.REQ0003.toString());
        }
        try {
            List<T> items = JSON.parseArray(JSON.toJSONString(body.get(name)), clazz);
            if (items == null) {
                throw new CommonException(String.format(ExceptionEnum.REQ0007.toString(), name));
            }
            return items;
        } catch (JSONException ex) {
            LOGGER.error(ExceptionEnum.REQ0004.toString(), ex);
            throw new CommonException(ExceptionEnum.REQ0004.toString());
        }
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
