package com.mao.sleeve.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mao.sleeve.exception.http.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: GenericAndJsonUtil
 * @Description: 自定义实体类的某些字段与json之间的转换规则
 * @Author 毛毛
 * @CreateDate 2021/11/11/周四 17:35
 * @Version: v1.0
 */
@Component
public class GenericAndJsonUtil {
    //@Resource
    //@Autowired
    /**
     * 不能直接给静态属性注入bean
     */
    private static ObjectMapper mapper;

    @Autowired
    //@Resource
    public void setMapper(ObjectMapper mapper) {
        GenericAndJsonUtil.mapper = mapper;
    }

    /**
     * 对象转json 序列化
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String objectToJson(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }

    /**
     * 反序列化
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }

    /**
     * 反序列化为集合list
     *
     * @param json
     * @param tr
     * @param <T>
     * @return
     */
    public static <T> T jsonToList(String json, TypeReference<T> tr) {
        if (json == null) {
            return null;
        }
        try {
            return mapper.readValue(json, tr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }
}
