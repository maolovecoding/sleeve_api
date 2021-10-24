package com.mao.sleeve.api.v1.controller.banner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BannerController
 * @Description: 广告横幅 广告接口
 * @Author 毛毛
 * @CreateDate 2021/10/18/周一 23:16
 * @Version: v1.0
 */
@RestController
@RequestMapping("/v1/banner")
public class BannerController {
    @GetMapping("/test")
    public String test() {
        return "毛毛！";
    }
}
