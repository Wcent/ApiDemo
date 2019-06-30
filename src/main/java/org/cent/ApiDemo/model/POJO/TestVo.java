package org.cent.ApiDemo.model.POJO;

import org.cent.ApiDemo.validator.DateTime;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Vincent
 * @version 1.0 2019/6/28
 */
public class TestVo {

    @Range(min = 1, max = 3, message = "取值范围1-3")
    private int anInt;

    @Range(max = 100, message = "最大值100")
    private long aLong;

    @NotBlank(message = "不能为空")
    @Length(max = 10, message = "长度不能超10个字符")
    private String string;

    @NotNull(message = "不能为空")
    @Digits(integer = 1, fraction = 2, message = "最多1位整数，2位小数")
    @DecimalMin(value = "0", inclusive = false, message = "必须大于0")
    private BigDecimal bigDecimal;

    @DateTime(type = DateTime.CheckType.datetime, pattern = "yyyyMMddHHmmss", message = "日期时间格式yyyyMMddHHmmss")
    private String datetime;

    @DateTime(type = DateTime.CheckType.date, pattern = "yyyy-MM-dd", message = "日期不能为空，格式yyyy-MM-dd")
    private String date;

    @DateTime(type = DateTime.CheckType.time, pattern = "HH:mm:ss", message = "时间格式为HH:mm:ss")
    private String time;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public long getaLong() {
        return aLong;
    }

    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TestVo{" +
                "anInt=" + anInt +
                ", aLong=" + aLong +
                ", string='" + string + '\'' +
                ", bigDecimal=" + bigDecimal +
                ", datetime='" + datetime + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
