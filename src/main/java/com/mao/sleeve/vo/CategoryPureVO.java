package com.mao.sleeve.vo;

import com.mao.sleeve.model.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName: CategoryPureVO
 * @Description: 分类 返回给前端的单个分类数据的精简对象
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 18:24
 * @Version: v1.0
 */
@Getter
@Setter
public class CategoryPureVO {
    private Long id;
    private String name;
    private Boolean isRoot;
    //private String description;
    private Long parentId;
    private String img;

    private Integer index;

    public CategoryPureVO(Category category) {
        BeanUtils.copyProperties(category, this);
    }
    public CategoryPureVO() {
    }

}
