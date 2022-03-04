package com.mao.sleeve.api.v1.token;

import com.mao.sleeve.dto.TokenGetDTO;
import com.mao.sleeve.exception.http.NotFoundException;
import com.mao.sleeve.service.authentication.AuthenticationService;
import com.mao.sleeve.service.authentication.WxAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TokenController
 * @Description: 令牌接口
 * @Author 毛毛
 * @CreateDate 2021/11/15/周一 16:03
 * @Version: v1.0
 */
@RestController
public class TokenController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private WxAuthenticationService wxAuthenticationService;

    /**
     * 获取令牌
     *
     * @param userData 用户信息
     * @return 令牌
     */
    @PostMapping("")
    public Map<String, String> getToken(@RequestBody @Validated TokenGetDTO userData) {
        System.out.println(userData);
        String token = "";
        switch (userData.getType()) {
            case USER_WX:
                // 微信登录
                token = wxAuthenticationService.codeToSession(userData.getAccount());
                break;
            case USER_EMAIL:
                // 查数据库
                break;
            default:
                throw new NotFoundException(10003);
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
