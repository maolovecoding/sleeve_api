package com.mao.sleeve.core.configuration;

import com.mao.sleeve.core.hack.AutoPrefixUrlMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @ClassName: AutoPrefixConfiguration
 * @Description: 路由前缀配置类
 * @Author 毛毛
 * @CreateDate 2021/10/25/周一 18:19
 * @Version: v1.0
 */
@Component
public class AutoPrefixConfiguration implements WebMvcRegistrations {
    /**
     * 实现路由前缀的配置 进行注册
     * @return
     */
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new AutoPrefixUrlMapping();
    }
}
