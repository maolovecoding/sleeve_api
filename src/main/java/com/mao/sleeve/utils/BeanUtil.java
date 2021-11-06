package com.mao.sleeve.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName: BeanUtil
 * @Description: 将数据库实体类转为视图对象vo返回给前端
 * @Author 毛毛
 * @CreateDate 2021/11/06/周六 11:02
 * @Version: v1.0
 */
public class BeanUtil {
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    /**
     * 单个对象的拷贝
     *
     * @param source
     * @param clazz
     * @return
     */
    public static <E, T> T beanToVO(E source, Class<T> clazz) {
        return mapper.map(source, clazz);
    }

    /**
     * 拷贝整个bean集合 转为 vo 集合
     *
     * @param sourceList
     * @param targetList
     * @param clazz
     * @param <E>
     * @param <T>
     */
    public static <E, T> void beanListTOVOList(List<E> sourceList, List<T> targetList, Class<T> clazz) {
        sourceList.forEach(source -> targetList.add(mapper.map(source, clazz)));
    }

    public static <E, T> void beanListTOVOList(Page<E> sourceList, List<T> targetList, Class<T> clazz) {
        sourceList.forEach(source -> targetList.add(mapper.map(source, clazz)));
    }
}
