package com.mao.sleeve.service.spu;

import com.mao.sleeve.model.Spu;
import org.springframework.data.domain.Page;


/**
 * @ClassName: SpuService
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/01/周一 18:29
 * @Version: v1.0
 */

public interface SpuService {
    /**
     * 根据id 获取spu
     *
     * @param id
     * @return
     */
    Spu getSpuById(Long id);

    /**
     * 获取最新的一组spu，且携带分页参数
     *
     * @param pageNum
     * @param size
     * @return
     */
    Page<Spu> getLatestPagingSpu(Integer pageNum, Integer size);

    /**
     * 根据分类id获取该分类下的所有spu
     *
     * @param categoryId 分类id
     * @param isRoot 是否是一级分类
     * @param pageNum 当前页
     * @param size 每页数量
     * @return
     */
    Page<Spu> getPagingSpuByCategoryId(Long categoryId, Boolean isRoot, Integer pageNum, Integer size);
}
