package com.mao.sleeve.core.hack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @ClassName: AutoPrefixUrlMapping
 * @Description: 自动补全controller路由前缀 覆盖方法 getMappingForMethod
 * @Author 毛毛
 * @CreateDate 2021/10/25/周一 18:07
 * @Version: v1.0
 */

public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {
    @Value("${sleeve.api-package}")
    private String apiPackagePath;

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        if (mappingInfo != null) {
            // 将我们的路由前缀加到路由上
            String prefix = getPrefix(handlerType);
            return RequestMappingInfo.paths(prefix).build().combine(mappingInfo);
        }
        return mappingInfo;
    }

    /**
     * 获取路由前缀 我们将controller所在的目录名称作为路由前缀
     *
     * @param handlerType
     * @return
     */
    private String getPrefix(Class<?> handlerType) {
        // 获取所在包目录结构 com.mao.sleeve.api.v1.controller....
        String packageName = handlerType.getPackage().getName();
        String prefix = packageName.replaceAll(apiPackagePath, "");
        // 拿到 /v1/xxx 层级 将所有的.都替换为 /
        // 使用 replaceAll("\\.","/")
        return prefix.replace(".", "/");
    }
}


