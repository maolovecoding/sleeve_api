package com.mao.sleeve.util;

import com.mao.sleeve.SleeveApplication;
import com.mao.sleeve.utils.GenericAndJsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @ClassName: JsonTest
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/11/周四 18:05
 * @Version: v1.0
 */
//@SpringBootTest(classes = SleeveApplication.class)
public class JsonTest {
    @Test
    public void test(){
        Optional<Object> optional = Optional.ofNullable("1");
        //System.out.println(optional);
        optional.map(String::valueOf).ifPresent(System.out::println);
    }
}
