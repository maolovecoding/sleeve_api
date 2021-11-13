package com.mao.sleeve;

import com.mao.sleeve.utils.GenericAndJsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SleeveApplicationTests {

    @Test
    public void test(){
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        String s = GenericAndJsonUtil.objectToJson(list);
        System.out.println(s);

    }

}
