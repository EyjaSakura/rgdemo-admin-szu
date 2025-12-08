package com.szu.admin.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JwtUtils {
    // 32+ 位字符串，私钥
    private static final String SECRET_KEY = "PleaseSocialiseWithMeImEyjaSakura";

    // token 有效期：7 天
    private static final long EXPIRATION = 7 * 24 * 60 * 60 * 1000;

    // 生成 token（只放必要信息）
    public static String generateToken(Long userId, String username, Integer role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("id", userId)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    // 解析 token
    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
