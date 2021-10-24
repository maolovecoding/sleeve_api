package com.mao.sleeve.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: UnifyResponse
 * @Description: 出现异常后 响应给前端的json类型的数据
 * @Author 毛毛
 * @CreateDate 2021/10/23/周六 23:31
 * @Version: v1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UnifyResponse {
    private Integer code;
    private String message;
    private String request;
}
