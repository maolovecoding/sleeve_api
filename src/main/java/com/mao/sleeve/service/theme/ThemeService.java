package com.mao.sleeve.service.theme;

import com.mao.sleeve.model.Theme;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName: ThemeService
 * @Description: 主题业务接口
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 20:28
 * @Version: v1.0
 */
public interface ThemeService {


    /**
     * 根据名称 查询主题
     *
     * @param names 主题名称集合
     * @return 所有主题
     */
    List<Theme> findByNames(List<String> names);

    /**
     * 根据主题名称 查询主题
     * @param name 主题名称
     * @return 主题数据
     */
    Optional<Theme> findByName(String name);
}
