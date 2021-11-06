package com.mao.sleeve.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName: Spu
 * @Description: sku模型类
 * @Author 毛毛
 * @CreateDate 2021/11/01/周一 18:15
 * @Version: v1.0
 */
@Entity
@Setter
@Getter
public class Spu extends BaseEntity {
    @Id
    private Long id;
    private String title;
    private String subtitle;
    /**
     * 所属的商品类别id
     */
    private Long categoryId;
    /**
     * 根商品类别 因为可能出现多级商品分类 这个字段是为了记录一级的分类id
     */
    private Long rootCategoryId;
    private Boolean online;
    private String price;
    /**
     * 可视规格值
     */
    private Long sketchSpecId;
    /**
     * 默认的sku规格值
     */
    private Long defaultSkuId;
    private String img;
    private String discountPrice;
    private String description;
    private String tags;
    private Boolean isTest;
    // 该字段对应数据库中的json数据类型 spu主题图片 实际上没用上该字段
    //private Object spuThemeImg;
    private String forThemeImg;

    /**
     * 配置导航关系
     * 单向一对多 必须要有@JoinColumn注解 且name属性是多方外键的名称
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spuId")
    private List<Sku> skuList;
    @OneToMany
    @JoinColumn(name = "spuId")
    private List<SpuImg> spuImgList;
    @OneToMany
    @JoinColumn(name = "spuId")
    private List<SpuDetailImg> spuDetailImgList;
}
