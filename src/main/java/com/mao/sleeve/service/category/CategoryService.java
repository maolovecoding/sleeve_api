package com.mao.sleeve.service.category;

import com.mao.sleeve.model.Category;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CategoryService
 * @Description: 分类 业务接口
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 15:48
 * @Version: v1.0
 */
public interface CategoryService {
    /**
     * 查询所有分类
     *
     * @return 查询出的分类集合 使用map包装
     */
    Map<Integer, List<Category>> getAll();
}
