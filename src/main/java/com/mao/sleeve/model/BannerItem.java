package com.mao.sleeve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @ClassName: BannerItem
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/10/31/周日 14:43
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
@Table(name = "banner_item")
public class BannerItem extends BaseEntity {
    @Id
    private Long id;
    private String img;
    private String keyword;
    private short type;
    private Long bannerId;
    private String name;
}
