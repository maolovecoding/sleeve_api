package com.mao.sleeve.model_bak;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName: Theme
 * @Description: 主题
 * @Author 毛毛
 * @CreateDate 2021/10/30/周六 15:05
 * @Version: v1.0
 */
//@Entity
@Table(name = "theme")
@Proxy(lazy = false)
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String name;
    /**
     * 关系维护端
     */
    @ManyToMany
    @JoinTable(name = "theme_spu",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            joinColumns = @JoinColumn(name = "theme_id"),
            inverseJoinColumns = @JoinColumn(name = "spu_id"))
    private List<Spu> spuList;
}
