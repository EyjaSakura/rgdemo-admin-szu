package com.szu.admin.config;

import com.szu.admin.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = request.getHeader("Authorization");

        if (token == null || token.isBlank()) {
            response.setStatus(401);
            response.getWriter().write("Unauthorized: token missing");
            return false;
        }

        try {
            Claims claims = JwtUtils.parseToken(token);

            // 把 claims 放到 request 中，Controller 可随时取用
            request.setAttribute("id", claims.get("id"));
            request.setAttribute("role", claims.get("role"));
        } catch (Exception e) {
            response.setStatus(401);
            response.getWriter().write("Unauthorized: token invalid");
            return false;
        }

        return true;
    }
}
