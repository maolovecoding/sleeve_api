package com.mao.sleeve.service.category;

import com.mao.sleeve.model.GridCategory;

import java.util.List;

/**
 * @ClassName: GridCategoryService
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 18:51
 * @Version: v1.0
 */
public interface GridCategoryService {
    /**
     * 获取所有的宫格分类数据 集合形式返回
     * @return 宫格数据
     */
    List<GridCategory> getGridCategoryList();

}
