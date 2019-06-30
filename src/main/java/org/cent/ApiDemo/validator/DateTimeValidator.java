package org.cent.ApiDemo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Vincent
 * @version 1.0 2019/6/30
 */
public class DateTimeValidator implements ConstraintValidator<DateTime, String> {

    // 是否必须有值
    private boolean required = true;
    // 检查类型，datetime/date/time
    private DateTime.CheckType type = DateTime.CheckType.datetime;
    // 日期时间校验格式
    private String pattern = "yyyy-MM-dd HH:mm:ss";
    private DateTimeFormatter formatter;

    @Override
    public void initialize(DateTime constraintAnnotation) {
        // 获取注解设置各条件限制
        required = constraintAnnotation.required();
        type = constraintAnnotation.type();
        pattern = constraintAnnotation.pattern();
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return !required;
        }

        // 解析成功为合法，异常则无效
        try {
            switch (type) {

                // 日期时间解析
                case datetime:
                    LocalDateTime.parse(value, formatter);
                    return true;

                // 日期解析
                case date:
                    LocalDate.parse(value, formatter);
                    return true;

                // 时间解析
                case time:
                    LocalTime.parse(value, formatter);
                    return true;

                // 异常解析
                default:
                    return false;
            }
        } catch (DateTimeParseException ex) {
            return false;
        }
    }
}
