package com.mao.sleeve.validators.anno;

import com.mao.sleeve.validators.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @ClassName: PasswordEqual
 * @Description: 比较两个密码是否相等
 * @Constraint(validatedBy = PasswordValidator.class) 指定使用该注解时，具体的校验实现是在哪个类中
 * @Author 毛毛
 * @CreateDate 2021/10/26/周二 20:37
 * @Version: v1.0
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordEqual {
    /**
     * 密码的最小值和最大值
     *
     * @return
     */
    int min() default 4;

    int max() default 18;

    /**
     * 校验失败的提示信息
     *
     * @return
     */
    String message() default "password are not equals";

    /**
     * 自定义校验注解的模板方法
     *
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 自定义校验注解的模板方法
     *
     * @return
     */
    Class<? extends Payload[]>[] payload() default {};


}
