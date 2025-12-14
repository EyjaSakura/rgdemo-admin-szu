package com.szu.admin.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtils {
    // 32+ 位字符串，私钥，转为base64格式
    private static final String SECRET_KEY = "PleaseSocialiseWithMeImEyjaSakura";

    // 获得KEY
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // token 有效期：7 天
    private static final long EXPIRATION = 7 * 24 * 60 * 60 * 1000;

    // 生成 token（只放必要信息）
    public static String generateToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("id", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY)
                .compact();
    }

    // 解析 token
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
