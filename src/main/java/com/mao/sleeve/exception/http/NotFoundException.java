package com.mao.sleeve.exception.http;

/**
 * @ClassName: NotFoundException
 * @Description: 请求资源未找到的异常
 * @Author 毛毛
 * @CreateDate 2021/10/23/周六 23:10
 * @Version: v1.0
 */
public class NotFoundException extends HttpException {
    /**
     *
     * @param code 错误码
     */
    public NotFoundException(int code) {
        this.httpStatusCode = 404;
        this.code = code;
    }
}
