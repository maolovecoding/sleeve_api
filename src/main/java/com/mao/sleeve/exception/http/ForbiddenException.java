package com.mao.sleeve.exception.http;

/**
 * @ClassName: ForbiddenException
 * @Description: 没有权限 异常 权限不足
 * @Author 毛毛
 * @CreateDate 2021/10/23/周六 23:12
 * @Version: v1.0
 */
public class ForbiddenException extends HttpException {
    public ForbiddenException(int code) {
        this.code = code;
        this.httpStatusCode = 403;
    }
}
