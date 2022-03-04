package com.mao.sleeve.dto.validators.anno;

import com.mao.sleeve.dto.validators.validator.TokenValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @ClassName: TokenPassword
 * @Description: 登录验证 密码验证 限制
 * @Author 毛毛
 * @CreateDate 2021/11/15/周一 16:09
 * @Version: v1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TokenValidator.class)
public @interface TokenPassword {
    int min() default 6;

    int max() default 20;

    String message() default "字段不符合要求";

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
