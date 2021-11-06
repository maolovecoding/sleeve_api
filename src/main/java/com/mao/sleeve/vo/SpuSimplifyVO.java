package com.mao.sleeve.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: SpuSimplifyVO
 * @Description: 返回给前端的概要数据 spu概要数据实体类
 * @Author 毛毛
 * @CreateDate 2021/11/02/周二 9:57
 * @Version: v1.0
 */
@Getter
@Setter
public class SpuSimplifyVO {
    private Long id;
    private String title;
    private String subtitle;

    private Boolean online;
    private String price;
    /**
     * 可视规格值
     */
    private Long sketchSpecId;
    private String img;
    private String discountPrice;
    private String description;
    private String tags;

    private String forThemeImg;

}
