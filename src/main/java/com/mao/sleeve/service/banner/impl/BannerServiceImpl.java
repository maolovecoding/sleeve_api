package com.mao.sleeve.service.banner.impl;

import com.mao.sleeve.model.Banner;
import com.mao.sleeve.repository.banner.BannerRepository;
import com.mao.sleeve.service.banner.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: BannerServiceImpl
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/10/29/周五 12:47
 * @Version: v1.0
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Banner getByName(String name) {
        return bannerRepository.findOneByName(name);
    }
}
