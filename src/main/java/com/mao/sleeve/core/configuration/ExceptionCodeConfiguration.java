package com.mao.sleeve.core.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: ExceptionCodeConfiguration
 * @Description: 异常错误码code配置类 读取错误码配置文件
 * @Author 毛毛
 * @CreateDate 2021/10/24/周日 21:58
 * @Version: v1.0
 */
@Getter
@PropertySource("classpath:config/exception-code.properties")
@ConfigurationProperties(prefix = "mao")
@Component
public class ExceptionCodeConfiguration {
    /**
     * mao.codes[40000] mao对应配置文件所有属性的前缀 codes会自动配置该属性
     */
    private Map<Integer, String> codes = new HashMap<>();

    /**
     * 根据错误码code获取错误消息
     *
     * @param code
     * @return
     */
    public String getMessage(int code) {
        return codes.get(code);
    }
}
