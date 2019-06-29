package org.cent.ApiDemo.util;

import org.hibernate.validator.HibernateValidator;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Set;

/**
 * 实例字段校验工具类
 * @author Vincent
 * @version 1.0 2019/6/29
 */
public class BeanValidatorUtil {

    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure()
            .failFast(false).buildValidatorFactory().getValidator();

    /**
     * 校验实例字段合法性
     * @param objName 实例名称
     * @param obj 实例
     * @param <T> 实例类型
     * @return 校验结果
     */
    public static <T> BindingResult validate(String objName, T obj) {
        Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(obj, Default.class);
        BindingResult bindingResult = new BindException(obj, objName);
        for (ConstraintViolation constraintViolation : constraintViolationSet) {
            bindingResult.addError(new FieldError(objName,
                    objName+"."+constraintViolation.getPropertyPath().toString(),
                    constraintViolation.getInvalidValue(),
                    true, null, null, constraintViolation.getMessage()));
        }

        return bindingResult;
    }

    /**
     * 校验集合中实例字段合法性
     * @param objName 实例名称
     * @param objList 实例集合
     * @param <T> 实例类型
     * @return 校验结果
     */
    public static <T> BindingResult validate(String objName, List<T> objList) {
        BindingResult bindingResult = new BindException(objList, objName);

        for (int i=0; i < objList.size(); i++) {
            Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(objList.get(i), Default.class);
            for (ConstraintViolation constraintViolation : constraintViolationSet) {
                bindingResult.addError(new FieldError(objName,
                        objName + "["+i+ "]." + constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getInvalidValue(),
                        true, null, null, constraintViolation.getMessage()));
            }

        }

        return bindingResult;
    }
}
