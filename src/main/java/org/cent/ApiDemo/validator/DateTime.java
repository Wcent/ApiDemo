package org.cent.ApiDemo.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {DateTimeValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTime {

    // 定义检查类型
    static public enum CheckType {datetime, date, time;}

    boolean required() default true;

    CheckType type() default CheckType.datetime;

    String pattern() default "yyyy-MM-dd HH:mm:ss";

    String message() default "日期时间格式yyyy-MM-dd HH:mm:ss";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
