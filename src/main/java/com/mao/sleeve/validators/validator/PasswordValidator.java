package com.mao.sleeve.validators.validator;

import com.mao.sleeve.validators.anno.PasswordEqual;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: PasswordValidator
 * @Description: 自定义校验注解的关联类 需要实现接口
 * ConstraintValidator<关联的自定义校验注解的类型, 自定义注解修饰的目标的类型（如果是修饰类，就是类的类型，如果是属性，就是属性的类型）>
 * @Author 毛毛
 * @CreateDate 2021/10/26/周二 20:50
 * @Version: v1.0
 */
public class PasswordValidator implements ConstraintValidator<PasswordEqual, Object> {
    /**
     * 拿到使用注解时  传入的参数
     * constraintAnnotation.min()  constraintAnnotation.max()
     * @param constraintAnnotation
     */
    @Override
    public void initialize(PasswordEqual constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
