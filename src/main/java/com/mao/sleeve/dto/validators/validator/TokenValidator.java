package com.mao.sleeve.dto.validators.validator;

import com.mao.sleeve.dto.validators.anno.TokenPassword;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: TokenValidator
 * @Description: token令牌的密码验证
 * @Author 毛毛
 * @CreateDate 2021/11/15/周一 16:15
 * @Version: v1.0
 */
public class TokenValidator implements ConstraintValidator<TokenPassword, String> {
    private Integer min;
    private Integer max;

    /**
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 空密码 一般是小程序的登录方式 携带的小程序的后台令牌
        if (StringUtils.isEmpty(s)) {
            return true;
        }
        // 不是小程序登录方式
        return s.length() >= min && s.length() <= max;
    }

    @Override
    public void initialize(TokenPassword constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }
}
