package com.mao.sleeve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ClassName: Sku
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/01/周一 20:44
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
public class Sku extends BaseEntity {
    @Id
    private Long id;
    /**
     * 原价
     */
    private BigDecimal price;
    /**
     * 折扣价
     */
    private BigDecimal discountPrice;
    /**
     * 商品是否还上线 也就是是否还在售卖
     */
    private Boolean online;
    private String img;
    private String title;
    private Long spuId;
    /**
     * 规格
     */
    private String specs;
    /**
     *
     */
    private String code;
    /**
     * 库存量
     */
    private Long stock;
    //private Object categoryId;
    //private Object rootCategoryId;
}
