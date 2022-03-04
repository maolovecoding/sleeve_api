package com.mao.sleeve.exception.http;

/**
 * @ClassName: ParameterException
 * @Description: 参数异常
 * @Author 毛毛
 * @CreateDate 2021/11/21/周日 14:12
 * @Version: v1.0
 */
public class ParameterException extends HttpException {

    public ParameterException(int code) {
        this.code = code;
        this.httpStatusCode = 401;
    }
}
