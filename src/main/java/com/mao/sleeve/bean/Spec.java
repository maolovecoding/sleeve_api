package com.mao.sleeve.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: Spec
 * @Description: 对应数据库表 sku表的specs字段，该字段存储规格 一个sku有多个规格（规格名+规格值）
 * @Author 毛毛
 * @CreateDate 2021/11/07/周日 20:37
 * @Version: v1.0
 */
@Getter
@Setter
public class Spec {
    /**
     * 规格名id
     */
    private Long keyId;
    /**
     * 规格名 规格名名称 name
     */
    private String key;
    /**
     * 规格值id
     */
    private Long valueId;
    /**
     * 规格值 值
     */
    private String value;
}
