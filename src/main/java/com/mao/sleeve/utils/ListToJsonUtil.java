package com.mao.sleeve.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mao.sleeve.exception.http.ServerErrorException;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * @ClassName: ListToJsonUtil
 * @Description: 集合类型属性和数据库json字段的相互转换
 * @Author 毛毛
 * @CreateDate 2021/11/07/周日 22:14
 * @Version: v1.0
 */
@Converter
public class ListToJsonUtil implements AttributeConverter<List<?>, String> {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(List<?> objects) {
        try {
            return mapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }

    @Override
    public List<?> convertToEntityAttribute(String s) {
        try {
            if (s == null) {
                // 空字符串 返回值应该是一个空集合，[] 而不是返回 null
                return Collections.emptyList();
            }
            return mapper.readValue(s, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }
}
