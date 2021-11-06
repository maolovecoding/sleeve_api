package com.mao.sleeve.vo;

import com.mao.sleeve.utils.BeanUtil;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PagingDozer
 * @Description:
 * @Author 毛毛
 * @CreateDate 2021/11/06/周六 15:17
 * @Version: v1.0
 */
public class PagingDozer<E, T> extends Paging<E> {
    public PagingDozer(Page<E> page, Class<T> clazz) {
        this.initPageParameters(page);
        List<E> content = page.getContent();
        List<T> targetList = new ArrayList<>();
        BeanUtil.beanListTOVOList(content, targetList, clazz);
        this.setItems(targetList);
    }
}
