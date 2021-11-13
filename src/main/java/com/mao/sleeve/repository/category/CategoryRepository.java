package com.mao.sleeve.repository.category;

import com.mao.sleeve.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName: CategoryRepository
 * @Description: 查询分类数据的方法
 * @Author 毛毛
 * @CreateDate 2021/11/11/周四 20:45
 * @Version: v1.0
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * 查询所有分类 可以是全部的一级分类 也可以是除一级分类以外的其他分类
     *
     * @param isRoot 是否查询所有根节点 true 所有根节点 false 非根节点
     * @return 所有根节点集合 / 非根节点集合
     */
    List<Category> findAllByIsRootOrderByIndexAsc(Boolean isRoot);
}
