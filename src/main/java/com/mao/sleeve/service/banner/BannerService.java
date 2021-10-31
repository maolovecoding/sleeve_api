package com.mao.sleeve.service.banner;

import com.mao.sleeve.model.Banner;

/**
 * @ClassName: BannerService
 * @Description: banner业务接口
 * @Author 毛毛
 * @CreateDate 2021/10/21/周四 13:31
 * @Version: v1.0
 */
public interface BannerService {
    /**
     * 根据名称拿到banner
     *
     * @param name
     * @return
     */
    Banner getByName(String name);
}
