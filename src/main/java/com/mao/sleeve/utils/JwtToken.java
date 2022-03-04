package com.mao.sleeve.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @ClassName: JwtToken
 * @Description: 用来生成Jwt令牌
 * @Author 毛毛
 * @CreateDate 2021/11/21/周日 19:28
 * @Version: v1.0
 */
@Component
public class JwtToken {
    /**
     * 加密字符串
     */
    private static String jwtKey;
    /**
     * 过期时间
     */
    private static Integer expiredTimeIn;
    /**
     * 默认用户的等级 8
     */
    private static final Integer DEFAULT_SCOPE = 8;


    @Value("${sleeve.security.jwtKey}")
    public void setJwtKey(String jwtKey) {
        JwtToken.jwtKey = jwtKey;
    }

    @Value("${sleeve.security.tokenExpiredIn}")
    public void setExpiredTimeIn(Integer expiredTimeIn) {
        JwtToken.expiredTimeIn = expiredTimeIn;
    }

    /**
     * 生成token令牌
     *
     * @param uid   用户表数据的唯一标识
     * @param scope 权限等级
     * @return 返回令牌
     */
    public static String makeToken(Long uid, Integer scope) {
        return getToken(uid, scope);
    }

    public static String makeToken(Long uid) {
        return getToken(uid, DEFAULT_SCOPE);
    }

    /**
     * 令牌验证
     * @param token 颁布的令牌
     * @return 解析令牌中的数据
     */
    public Optional<Map<String, Claim>> getClaims(String token) {
        // 加密算法
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        // 生成验证token的验证对象
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        // 验证后返回解析后的jwt令牌 可以从令牌中获取我们加密前的数据
        DecodedJWT verify;
        try {
            verify = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            // 验证失败 token失效 或者无token错误
            // 可以区分 是token过期  还是token是非法的
            return Optional.empty();
        }
        // 写到jwt中的加密数据
        Map<String, Claim> claims = verify.getClaims();
        return Optional.of(claims);
    }

    /**
     * 实际生成token
     *
     * @param uid   用户表数据的唯一标识
     * @param scope 权限等级
     * @return 返回令牌
     */
    private static String getToken(Long uid, Integer scope) {
        // jjwt，auth0 用来生成令牌
        // 1. 指定加密算法
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        Map<String, Date> map = calculateExpiredIssues();
        return JWT.create()
                // 写入加密数据到jwt中
                .withClaim("uid", uid)
                .withClaim("scope", scope)
                // 指定过期时间
                .withExpiresAt(map.get("expiredTime"))
                // 指定签发时间
                .withIssuedAt(map.get("now"))
                // 指定jwt加密算法
                .sign(algorithm);
    }

    /**
     * 令牌签发
     *
     * @return 令牌签发 时间，失效时间
     */
    private static Map<String, Date> calculateExpiredIssues() {
        Map<String, Date> map = new HashMap<>(4);
        // 令牌签发时间
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        // 令牌过期时间
        calendar.add(Calendar.SECOND, expiredTimeIn);

        map.put("now", now);
        map.put("expiredTime", calendar.getTime());
        return map;
    }

}
