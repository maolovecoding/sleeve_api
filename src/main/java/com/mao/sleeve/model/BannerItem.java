package com.mao.sleeve.model;


import javax.persistence.*;

/**
 * @ClassName: BannerItem
 * @Description: banner模型子项 一个个广告项
 * @Author 毛毛
 * @CreateDate 2021/10/29/周五 19:09
 * @Version: v1.0
 */
@Entity
@Table(name = "banner_item")
public class BannerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String img;
    /**
     * 关键字 跳转到spu的时候，需要携带spu的id 跳转到专题的时候，需要携带的是专题的标识
     */
    private String keyword;
    /**
     * TODO 点击这个广告，应该跳转到spu商品页面，还是专题页面，主题页面等
     */
    private Short type;
    /**
     * 外键 属于那个banner
     * TODO JPA会自动帮我们创建，不需要显示手写，也不能定义
     * 如果想要显示的定义该属性，需要在下面的导航属性上的@joinColumn注解上配置一些属性
     */
    private Long bannerId;
    /**
     * 当前banner子项属于的banner
     * insertable = false,updatable = false 关闭可插入和可更新机制
     */
    @ManyToOne
    @JoinColumn(name = "bannerId", insertable = false,updatable = false,foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Banner banner;
}
