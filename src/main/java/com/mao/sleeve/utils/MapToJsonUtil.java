package com.mao.sleeve.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mao.sleeve.exception.http.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MapToJsonUtil
 * @Description: 将map转为json 或者json反序列化为map
 * 我们希望jpa在进行序列化和反序列化的过程中，自动调用这个类的方法进行属性的序列化和数据库字段的反序列化
 * 只需要实现接口 AttributeConverter<属性的类型，序列化的类型> 将那种属性的类型转为数据库的那种类型
 * @Author 毛毛
 * @CreateDate 2021/11/07/周日 21:15
 * @Version: v1.0
 */
@Converter
public class MapToJsonUtil implements AttributeConverter<Map<String, ?>, String> {
    @Autowired
    private ObjectMapper mapper;

    /**
     * 模型属性 转数据库字段
     *
     * @param map 属性值
     * @return 序列化后的json字符串
     */
    @Override
    public String convertToDatabaseColumn(Map<String, ?> map) {
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // TODO 记录日志
            // 9999 服务器未知异常 内部错误
            throw new ServerErrorException(9999);
        }
    }

    /**
     * 数据库字段 转模型属性
     *
     * @param s 数据库中的json数据类型 在java中我们只能用字符串来代替
     * @return 反序列化后的对象 变成对象的具体字段
     */
    @Override
    public Map<String, ?> convertToEntityAttribute(String s) {
        try {
            // 反序列化
            return mapper.readValue(s, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }
}
