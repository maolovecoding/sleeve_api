package com.mao.sleeve.model;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName: Spu
 * @Description: 商品
 * @Author 毛毛
 * @CreateDate 2021/10/30/周六 15:05
 * @Version: v1.0
 */
@Entity
@Table(name = "spu")
public class Spu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 主标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subTitle;
    /**
     * 关系被维护端
     * 忽略物理外键
     */
    @ManyToMany(mappedBy = "spuList")
    //@org.hibernate.annotations.ForeignKey(name = "null")
    // 上面是被废弃的方式，下面的方式是新增的，也是用来禁止生成物理外键的，设置模式是无限制
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<Theme> themeList;
}
