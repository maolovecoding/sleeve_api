package com.mao.sleeve.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName: Paging
 * @Description: 返回给前端的分页对象
 * @Author 毛毛
 * @CreateDate 2021/11/06/周六 14:50
 * @Version: v1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class Paging<T> {
    /**
     * 数据的总数量
     */
    private Long total;
    /**
     * 当前的数据条数
     */
    private Integer count;
    /**
     * 当前是第几页
     */
    private Integer page;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 数据集合 这里使用泛型通配符是最好的解决方案！
     */
    private List<?> items;

    public Paging(Page<T> page) {
        initPageParameters(page);
    }

    protected void initPageParameters(Page<T> page) {
        this.total = page.getTotalElements();
        this.count = page.getSize();
        this.page = page.getNumber();
        this.totalPage = page.getTotalPages();
        this.items = page.getContent();
    }
}
