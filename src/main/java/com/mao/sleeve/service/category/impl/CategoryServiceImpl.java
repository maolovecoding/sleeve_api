package com.mao.sleeve.service.category.impl;

import com.mao.sleeve.model.Category;
import com.mao.sleeve.repository.category.CategoryRepository;
import com.mao.sleeve.service.category.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CategoryServiceImpl
 * @Description: 分类 业务接口实现
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 15:48
 * @Version: v1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public Map<Integer, List<Category>> getAll() {
        // 一级分类
        List<Category> roots = categoryRepository.findAllByIsRootOrderByIndexAsc(true);
        // 本项目中是全部的二级分类
        List<Category> subs = categoryRepository.findAllByIsRootOrderByIndexAsc(true);
        Map<Integer, List<Category>> categories = new HashMap<>();
        categories.put(1, roots);
        categories.put(2, subs);
        return categories;
    }
}
