package org.cent.ApiDemo.model.POJO;

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

    @Override
    public String toString() {
        return "TestVo{" +
                "anInt=" + anInt +
                ", aLong=" + aLong +
                ", string='" + string + '\'' +
                ", bigDecimal=" + bigDecimal +
                '}';
    }
}
