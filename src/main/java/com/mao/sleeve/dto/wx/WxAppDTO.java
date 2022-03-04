package com.mao.sleeve.dto.wx;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: WxAppDTO
 * @Description: 向微信平台发起登录验证请求 返回的json对象 -> 类对象
 * @Author 毛毛
 * @CreateDate 2021/11/18/周四 14:22
 * @Version: v1.0
 */
@Getter
@Setter
public class WxAppDTO {
    /**
     * 用户唯一标识
     */
    private String openid;
    /**
     * 会话密钥
     */
    private String sessionKey;
    /**
     * 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回
     */
    private String unionId;
    /**
     * 错误码
     */
    private Long errCode;
    /**
     * 错误消息
     */
    private String errMsg;
}
