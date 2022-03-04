package com.mao.sleeve.anno;

import com.auth0.jwt.JWT;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @ClassName: JwtTest
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/21/周日 19:58
 * @Version: v1.0
 */
public class JwtTest {

    @Test
    public void test1(){
        Instant now = Instant.now();
        System.out.println(now.toEpochMilli());
        System.out.println(System.currentTimeMillis());
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);
        now1.plusSeconds(11111111L);
    }
}
