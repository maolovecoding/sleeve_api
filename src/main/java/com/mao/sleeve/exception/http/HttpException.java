package com.mao.sleeve.exception.http;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: HttpException
 * @Description: http异常 基类
 * @Author 毛毛
 * @CreateDate 2021/10/23/周六 23:07
 * @Version: v1.0
 */
@Getter
@Setter
public class HttpException extends RuntimeException {
    /**
     * 自定义的错误（状态）码
     */
    protected Integer code;
    /**
     * http状态码
     */
    protected Integer httpStatusCode = 500;

}
