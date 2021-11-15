package com.mao.sleeve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName: Theme
 * @Description: 主题，也可以叫专题 在本项目中 没有区别
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 19:14
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
public class Theme extends BaseEntity {
    @Id
    private Long id;
    private String title;
    private String description;
    private String name;
    private String extend;
    private String entranceImg;
    /**
     * 主题是否有对整个主题的描述图片，比如每周上新图片 需要在主题的上方
     */
    private String internalTopImg;
    private Boolean online;
    private String titleImg;
    /**
     * 指定当前主题 在前端应用什么模板 模板名称
     * 在小程序端可以创建多个模板，然后通过我们服务器传递过去的模板名称，
     * 可以动态的决定主题使用哪个模板，不需要前端修改代码
     * 数据会填充到指定的模板里面
     */
    private String tplName;
    @ManyToMany
    @JoinTable(name = "theme_spu",
            joinColumns = @JoinColumn(name = "theme_id"),
            inverseJoinColumns = @JoinColumn(name = "spu_id")
    )
    private List<Spu> spuList;
}
