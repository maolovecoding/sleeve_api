package com.mao.sleeve.vo;

import com.mao.sleeve.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: CategoriesVO
 * @Description: 所有的分类数据
 * @Author 毛毛
 * @CreateDate 2021/11/11/周四 20:44
 * @Version: v1.0
 */
@Getter
@Setter
public class CategoriesVO {
    /**
     * 一级分类
     */
    private List<CategoryPureVO> roots;
    /**
     * 二级分类
     */
    private List<CategoryPureVO> subs;

    public CategoriesVO(Map<Integer, List<Category>> categories) {
        // category -> categoryPureVO
        this.roots = categories.get(1).stream()
                // .map(category-> new CategoryPureVO(category))
                .map(CategoryPureVO::new)
                // 将流转为集合
                .collect(Collectors.toList());
        this.subs = categories.get(2).stream()
                .map(CategoryPureVO::new)
                // 将流转为集合
                .collect(Collectors.toList());
    }
}
