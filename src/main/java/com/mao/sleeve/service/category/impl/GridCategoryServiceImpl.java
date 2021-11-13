package com.mao.sleeve.service.category.impl;

import com.mao.sleeve.model.GridCategory;
import com.mao.sleeve.repository.category.GridCategoryRepository;
import com.mao.sleeve.service.category.GridCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: GridCategoryServiceImpl
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 18:52
 * @Version: v1.0
 */
@Service
public class GridCategoryServiceImpl implements GridCategoryService {
    @Autowired
    private GridCategoryRepository gridCategoryRepository;
    @Override
    public List<GridCategory> getGridCategoryList() {
        return gridCategoryRepository.findAll();
    }
}
