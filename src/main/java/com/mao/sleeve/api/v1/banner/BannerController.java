package com.mao.sleeve.api.v1.banner;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BannerController
 * @Description: 广告横幅 广告接口 指定的前缀全都在 AutoPrefixUrlMapping 类中规定好了，前缀都是目录名称
 * @Author 毛毛
 * @CreateDate 2021/10/18/周一 23:16
 * @Version: v1.0
 */
@RestController
@Validated
public class BannerController {
    @GetMapping("/test")
    public String test() {
        return "毛毛！";
    }
}
