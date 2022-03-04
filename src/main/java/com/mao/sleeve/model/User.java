package com.mao.sleeve.model;

import com.mao.sleeve.utils.MapToJsonUtil;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Map;

/**
 * @ClassName: User
 * @Description: 用户表
 * @Author 毛毛
 * @CreateDate 2021/11/21/周日 14:15
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "delete_time is null")
@ToString
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String openid;
    /**
     * 昵称
     */
    private String nickname;
    private String email;
    private String mobile;
    private String password;
    private Integer unifyUid;
    /**
     * 存储微信里面的用户资料
     */
    @Convert(converter = MapToJsonUtil.class)
    private Map<String, ?> wxProfile;
    /**
     * 分级标识 青铜 黄金 砖石 王者  分组 -》 角色 可以用来加入会员系统
     * 有需要的话可以设计表 设计类 进行操作
     */
    //private String group;
}
