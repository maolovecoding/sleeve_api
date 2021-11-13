package com.mao.sleeve.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName: Banner
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/10/31/周日 14:43
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
@Where(clause = "delete_time is null")
public class Banner extends BaseEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    private String title;
    private String img;
    /**
     * 不访问该属性，不会查询相关的导航属性的数据 懒加载
     * 传递数据给前端的时候，会进行数据的JSON话，也会读取数据，所以也会查询相关数据
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bannerId")
    private List<BannerItem> items;

}
