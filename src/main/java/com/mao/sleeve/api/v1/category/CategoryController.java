package com.mao.sleeve.api.v1.category;

import com.mao.sleeve.exception.http.NotFoundException;
import com.mao.sleeve.model.Category;
import com.mao.sleeve.model.GridCategory;
import com.mao.sleeve.service.category.CategoryService;
import com.mao.sleeve.service.category.GridCategoryService;
import com.mao.sleeve.vo.CategoriesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CategoryController
 * @Description: 分类相关接口 获取一级，二级等分类 获取宫格分类
 * @Author 毛毛
 * @CreateDate 2021/11/11/周四 20:33
 * @Version: v1.0
 */
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GridCategoryService gridCategoryService;

    @GetMapping("/all")
    public CategoriesVO getAll() {
        Map<Integer, List<Category>> categories = categoryService.getAll();
        return new CategoriesVO(categories);
    }

    @GetMapping("/grid/all")
    public List<GridCategory> getGridCategoryList() {
        List<GridCategory> gridCategoryList = gridCategoryService.getGridCategoryList();
        if(gridCategoryList.isEmpty()){
            throw new NotFoundException(30009);
        }
        return gridCategoryList;
    }
}
