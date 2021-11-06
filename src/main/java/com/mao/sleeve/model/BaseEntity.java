package com.mao.sleeve.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @ClassName: BaseEntity
 * @Description: 模型类基类
 * @Author 毛毛
 * @CreateDate 2021/10/31/周日 15:00
 * @Version: v1.0
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    /**
     * 三个基础字段 且这三个字段 不应该返回给前端 也就是不应该序列化
     *
     * @JsonIgnore: 序列化的时候，忽略标记的属性
     */
    @JsonIgnore
    protected Date createTime;
    @JsonIgnore
    protected Date updateTime;
    @JsonIgnore
    protected Date deleteTime;
}
