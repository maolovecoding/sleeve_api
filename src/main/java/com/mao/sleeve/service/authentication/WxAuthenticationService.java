package com.mao.sleeve.service.authentication;

import com.mao.sleeve.dto.wx.WxAppDTO;

/**
 * @ClassName: WxAuthenticationService
 * @Description: 微信登录 业务接口
 * @Author 毛毛
 * @CreateDate 2021/11/15/周一 17:45
 * @Version: v1.0
 */
public interface WxAuthenticationService {
    /**
     * 微信登录验证
     *
     * @param code 微信小程序后台code码
     * @return 返回生成的令牌
     */
    String codeToSession(String code);

    /**
     * 进行用户登录
     * @param wxAppDTO 微信验证后的对象
     * @return 注册成功后 返回jwt令牌
     */
    String registerUser(WxAppDTO wxAppDTO);
}
