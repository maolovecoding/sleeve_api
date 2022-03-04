package com.mao.sleeve.service.authentication.impl;

import com.mao.sleeve.dto.TokenGetDTO;
import com.mao.sleeve.service.authentication.AuthenticationService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: AuthenticationServiceImpl
 * @Description: 用户登录 业务处理实现
 * @Author 毛毛
 * @CreateDate 2021/11/15/周一 17:41
 * @Version: v1.0
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public void getTokenByEmail(TokenGetDTO userData) {

    }

    @Override
    public void validateByWx(TokenGetDTO userData) {

    }

    @Override
    public void register() {

    }
}
