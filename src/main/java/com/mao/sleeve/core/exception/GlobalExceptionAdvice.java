package com.mao.sleeve.core.exception;

import com.mao.sleeve.core.configuration.ExceptionCodeConfiguration;
import com.mao.sleeve.core.response.UnifyResponse;
import com.mao.sleeve.exception.http.HttpException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: GlobalExceptionAdvice
 * @Description: 全局异常处理  TODO 未知异常的code统一为9999
 * 通过异常的形式 响应给前端消息 响应的数据其实就是json形式 包含状态码和错误信息
 * @Author 毛毛
 * @CreateDate 2021/10/23/周六 13:05
 * @Version: v1.0
 */
@ControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * 属性注入 注入异常配置类 用来获取所有已知的异常错误码对应的错误信息
     */
    @Autowired
    private ExceptionCodeConfiguration exceptionCodes;
    /**
     * 全局处理异常 处理任意类型异常
     *
     * @ExceptionHandler 异常处理器
     * @ResponseStatus(code = HttpStatus.BAD_REQUEST) 修改请求状态码为 400 HttpStatus 枚举类型 spring提供的
     * @ExceptionHandler(value = Exception.class) value的值就是当前处理器处理的异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public UnifyResponse handleException(HttpServletRequest req, Exception e) {
        // 未知异常的code统一为9999
        String url = req.getRequestURI();
        // 请求方式
        String method = req.getMethod();
        UnifyResponse message = new UnifyResponse(9999, "未知异常！", method + " " + url);
        // TODO 开发阶段打印异常  记录日志
        System.out.println(e);
        return message;
    }

    /**
     * 处理http异常
     *
     * @param req
     * @param httpException
     */
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req, HttpException httpException) {
        // 未知异常的code统一为9999
        String url = req.getRequestURI();
        // 请求方式
        String method = req.getMethod();
        UnifyResponse message = new UnifyResponse(httpException.getCode(), exceptionCodes.getMessage(httpException.getCode()), method + " " + url);
        // 响应头
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置响应头
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        // http状态码
        HttpStatus httpStatus = HttpStatus.resolve(httpException.getHttpStatusCode());
        assert httpStatus != null;
        return new ResponseEntity<>(message, httpHeaders, httpStatus);
    }
}
