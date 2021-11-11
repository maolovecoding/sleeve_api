package com.mao.sleeve.exception.http;

/**
 * @ClassName: ServerErrorException
 * @Description: 服务器内部错误
 * @Author 毛毛
 * @CreateDate 2021/11/07/周日 21:35
 * @Version: v1.0
 */
public class ServerErrorException extends HttpException {
    public ServerErrorException(int code) {
        this.httpStatusCode = 500;
        this.code = code;
    }
}
