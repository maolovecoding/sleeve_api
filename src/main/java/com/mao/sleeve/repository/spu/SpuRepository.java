package com.mao.sleeve.repository.spu;

import com.mao.sleeve.model.Spu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: SpuRepository
 * @Description: spu持久层接口
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

    /**
     * 根据分类id查询对应的spu
     *
     * @param categoryId
     * @param pageable
     * @return
     */
    Page<Spu> findByCategoryIdOrderByCreateTimeDesc(Long categoryId, Pageable pageable);

    /**
     * 查询根分类id对应的spu
     * @param categoryId
     * @param pageable
     * @return
     */
    Page<Spu> findByRootCategoryIdOrderByCreateTimeDesc(Long categoryId, Pageable pageable);

}
