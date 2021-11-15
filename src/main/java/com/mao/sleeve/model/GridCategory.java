package com.mao.sleeve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * @ClassName: GridCategory
 * @Description: 宫格分类 前端页面首页展示的那些宫格对应的分类实体对象
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 18:45
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
public class GridCategory extends BaseEntity{
    @Id
    private Long id;
    private String title;
    private String img;
    private String name;
    private Long categoryId;
    private Long rootCategoryId;

}
