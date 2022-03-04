package com.mao.sleeve.service.authentication;

import com.mao.sleeve.dto.TokenGetDTO;

/**
 * @ClassName: AuthenticationService
 * @Description: 用户登录校验 登录授权校验
 * @Author 毛毛
 * @CreateDate 2021/11/15/周一 17:39
 * @Version: v1.0
 */
public interface AuthenticationService {
    /**
     * 邮箱登录
     *
     * @param userData
     */
    void getTokenByEmail(TokenGetDTO userData);

    /**
     * wx登录
     *
     * @param userData
     */
    void validateByWx(TokenGetDTO userData);

    /**
     * 首次登录注册
     */
    void register();
}
