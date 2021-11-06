package com.mao.sleeve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * @ClassName: SpuImg
 * @Description: spu的图片 (可以理解为轮播图，首图等) 模型类
 * @Author 毛毛
 * @CreateDate 2021/11/01/周一 20:35
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
public class SpuImg extends BaseEntity {
    @Id
    private Long id;
    /**
     * 图片地址
     */
    private String img;
    private Long spuId;

}
