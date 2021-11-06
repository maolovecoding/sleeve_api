package com.mao.sleeve.repository.spu;

import com.mao.sleeve.model.Spu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: SpuRepository
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/01/周一 18:31
 * @Version: v1.0
 */
public interface SpuRepository extends JpaRepository<Spu, Long> {
    /**
     * 根据id查询spu商品
     *
     * @param id
     * @return
     */
    Spu findOneById(Long id);
}
