package com.mao.sleeve.api.v1.theme;

import com.mao.sleeve.exception.http.NotFoundException;
import com.mao.sleeve.model.Theme;
import com.mao.sleeve.service.theme.ThemeService;
import com.mao.sleeve.utils.BeanUtil;
import com.mao.sleeve.vo.ThemePureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: ThemeController
 * @Description: 主题数据相关接口
 * @Author 毛毛
 * @CreateDate 2021/10/31/周日 20:49
 * @Version: v1.0
 */
@RestController
@Validated
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    /**
     * 根据名称获取主题接口
     *
     * @param names 主题名称，字符串，需要以逗号进行分割
     * @return 所有的主题
     */
    @GetMapping("/by/names")
    public List<ThemePureVO> getThemeGroupByNames(@RequestParam String names) {
        List<String> nameList = Arrays.asList(names.split(","));
        List<Theme> themes = themeService.findByNames(nameList);
        List<ThemePureVO> list = new ArrayList<>();
        BeanUtil.beanListTOVOList(themes, list, ThemePureVO.class);
        return list;
    }

    @GetMapping("/name/{name}/with_spu")
    public Theme getThemeByNameWithSpu(@PathVariable("name") String themeName) {
        Optional<Theme> theme = themeService.findByName(themeName);
        // 如果theme不存在 会抛出异常
        return theme.orElseThrow(() -> new NotFoundException(30003));
    }
}
