package com.mao.sleeve.utils;

import com.mao.sleeve.bo.PageCounter;

/**
 * @ClassName: CommonUtil
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/06/周六 12:37
 * @Version: v1.0
 */
public class CommonUtil {
    /**
     * 获取分页参数
     *
     * @param start
     * @param count
     * @return
     */
    public static PageCounter convertToPageParameter(Integer start, Integer count) {
        int pageNum = start / count;
        return PageCounter.builder()
                .pageNum(pageNum)
                .size(count)
                .build();
    }
}
