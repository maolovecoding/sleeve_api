package com.mao.sleeve.dto;

import com.mao.sleeve.type.LoginType;
import com.mao.sleeve.dto.validators.anno.TokenPassword;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: TokenGetDTO
 * @Description: 令牌数据传输对象
 * @Author 毛毛
 * @CreateDate 2021/11/15/周一 16:06
 * @Version: v1.0
 */
@Getter
@Setter
@ToString
public class TokenGetDTO {
    /**
     * 账号
     */
    @NotBlank(message = "account不允许为空")
    private String account;
    /**
     * 密码
     */
    @TokenPassword(min = 6, max = 20, message = "{token.password}")
    private String password;
    /**
     * 登录类型
     */
    private LoginType type;
}
