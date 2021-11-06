package com.mao.sleeve.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: PageCounter
 * @Description: 业务对象 将start和count转为pageNum和size
 * @Author 毛毛
 * @CreateDate 2021/11/06/周六 12:58
 * @Version: v1.0
 */
@Getter
@Setter
@Builder
public class PageCounter {
    private Integer pageNum;
    private Integer size;
}
