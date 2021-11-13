package com.mao.sleeve.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName: Category
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/11/周四 20:46
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
public class Category extends BaseEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    private Boolean isRoot;
    private Long parentId;
    private String img;
    /**
     * 前端可以安装索引进行分类的排序，我觉得Integer已经够用
     */
    private Integer index;
    private Boolean online;
    /**
     * 分类等级
     */
    private Short level;
}
