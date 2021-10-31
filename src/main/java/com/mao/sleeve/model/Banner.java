package com.mao.sleeve.model;

import lombok.Getter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName: Banner
 * @Description: banner模型类
 * @Author 毛毛
 * @CreateDate 2021/10/29/周五 15:01
 * @Version: v1.0
 */
@Entity
@Table(name = "banner")
@Proxy(lazy = false)
@Getter
public class Banner {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * @Column(length = 16) 限制长度不超过16
     */
    @Column(length = 16, name = "name")
    private String name;
    /**
     * 描述
     *
     * @Transient: 该注解实现 标注的属性不在表中体现
     */
    @Transient
    private String description;
    /**
     * 广告板块首图
     */
    private String img;
    private String title;
    /**
     * 一个banner拥有多个item，一对多
     * mappedBy = "banner" 该属性的值就是多端维护关系的属性的属性名，也就是导航属性名
     */
    @OneToMany(mappedBy = "banner")
    //@JoinColumn(name = "banner_id")
    private List<BannerItem> bannerItems;
}
