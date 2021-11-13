package com.mao.sleeve.repository.category;

import com.mao.sleeve.model.GridCategory;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @ClassName: GridCategoryRepository
 * @Description: 宫格分类 dao接口
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 18:49
 * @Version: v1.0
 */
public interface GridCategoryRepository extends JpaRepository<GridCategory,Long> {
}
