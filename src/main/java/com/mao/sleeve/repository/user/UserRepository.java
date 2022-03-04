package com.mao.sleeve.repository.user;

import com.mao.sleeve.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @ClassName: UserRepository
 * @Description: 操作用户表
 * @Author 毛毛
 * @CreateDate 2021/11/21/周日 14:31
 * @Version: v1.0
 */
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     *
     * @param email 邮箱登录
     * @return
     */
    Optional<User> findByEmail(String email);

    /**
     *
     * @param openid 微信登录的openid
     * @return
     */
    User findByOpenid(String openid);

    /**
     *
     * @param id 用户id
     * @return
     */
    Optional<User> findFirstById(Long id);

    /**
     *
     * @param uuid
     * @return
     */
    Optional<User> findByUnifyUid(long uuid);
}
