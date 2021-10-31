package com.mao.sleeve.repository.banner;

import com.mao.sleeve.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: BannerRepository
 * @Description: Repository 仓储模式 使用jpa操作数据库，读写
 * JpaRepository<Banner,Long>
 * 泛型一： 是我们操作的表对应的模型的类型
 * 泛型二： 是操作表的主键的类型
 * @Author 毛毛
 * @CreateDate 2021/10/29/周五 23:54
 * @Version: v1.0
 */
public interface BannerRepository extends JpaRepository<Banner, Long> {
    /**
     * 根据id查询相应的banner
     *
     * @param id
     * @return
     */
    Banner findOneById(Long id);

    /**
     * 根据name查询相应的banner
     *
     * @param name
     * @return
     */
    Banner findOneByName(String name);
}
