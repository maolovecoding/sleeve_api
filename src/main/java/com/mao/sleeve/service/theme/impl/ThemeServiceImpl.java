package com.mao.sleeve.service.theme.impl;

import com.mao.sleeve.model.Theme;
import com.mao.sleeve.repository.theme.ThemeRepository;
import com.mao.sleeve.service.theme.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName: ThemeServiceImpl
 * @Description: 主题业务接口实现
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 20:28
 * @Version: v1.0
 */
@Service
public class ThemeServiceImpl implements ThemeService {
    @Autowired
    private ThemeRepository themeRepository;
    @Override
    public List<Theme> findByNames(List<String> names) {
        return themeRepository.findByNames(names);
    }

    @Override
    public Optional<Theme> findByName(String name) {
        return themeRepository.findByName(name);
    }
}
