package com.mao.sleeve.service.authentication.impl;

import com.mao.sleeve.dto.wx.WxAppDTO;
import com.mao.sleeve.exception.http.ParameterException;
import com.mao.sleeve.model.User;
import com.mao.sleeve.repository.user.UserRepository;
import com.mao.sleeve.service.authentication.WxAuthenticationService;
import com.mao.sleeve.utils.GenericAndJsonUtil;
import com.mao.sleeve.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * 微信登录
 *
 * @ClassName: WxAuthenticationServiceImpl
 * @Description: 微信登录业务接口实现
 * @Author 毛毛
 * @CreateDate 2021/11/15/周一 17:46
 * @Version: v1.0
 */
@Service
public class WxAuthenticationServiceImpl implements WxAuthenticationService {
    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.appSecret}")
    private String appSecret;
    @Value("${wx.code2sessionUrl}")
    private String code2sessionUrl;

    @Autowired
    private UserRepository userRepository;


    /**
     * 验证微信登录身份
     * 用code码换取用户的openid
     *
     * @param code 微信小程序后台code码
     * @return 颁布验证身份成功后的令牌
     */
    @Override
    public String codeToSession(String code) {
        // 拼接微信登录校验的请求地址
        String url = MessageFormat.format(code2sessionUrl, appid, appSecret, code);
        // 发起请求
        RestTemplate restTemplate = new RestTemplate();
        String sessionText = restTemplate.getForObject(url, String.class);
        // 微信接口认证 返回的json转对象
        WxAppDTO wxAppDTO = GenericAndJsonUtil.jsonToObject(sessionText, WxAppDTO.class);
        // 判断用户是否注册过 没注册 自动后台注册 然后我们数据库中的唯一标识写到jwt中
        return registerUser(wxAppDTO);
    }

    @Override
    public String registerUser(WxAppDTO wxAppDTO) {
        String openid = wxAppDTO.getOpenid();
        if (openid == null || "".equals(openid)) {
            throw new ParameterException(20004);
        }
        User user = userRepository.findByOpenid(openid);
        System.out.println(user);
        if (user != null) {
            // TODO jwt令牌
            return JwtToken.makeToken(user.getId());
        }
        // 用户不存在 第一次登录 需要进行持久化
        User newUser = userRepository.save(User
                .builder()
                .openid(openid)
                .build());
        System.out.println(newUser);
        // 持久化以后 返回jwt令牌
        return JwtToken.makeToken(newUser.getId());
    }
}
