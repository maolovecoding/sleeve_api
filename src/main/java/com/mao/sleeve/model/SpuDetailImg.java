package com.mao.sleeve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName: SpuDetailImg
 * @Description: spu 商品详情图 模型类
 * @Author 毛毛
 * @CreateDate 2021/11/01/周一 20:38
 * @Version: v1.0
 */
@Entity
@Setter
@Getter
public class SpuDetailImg {
    @Id
    private Long id;
    private String img;
    private Long spuId;
    /**
     * 每张详情图的索引 图片是有顺序的 按照索引index进行排序
     */
    private Long index;


}
