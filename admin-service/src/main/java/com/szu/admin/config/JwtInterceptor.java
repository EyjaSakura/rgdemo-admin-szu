package com.szu.admin.config;

import com.szu.admin.common.annotation.RequireRoot;
import com.szu.admin.common.annotation.RequireSelf;
import com.szu.admin.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println(">>> JwtInterceptor: " + request.getMethod());

        // 放行OPTIONS，防止前端请求被浏览器拦截
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String token = request.getHeader("Authorization");

        if (token == null || token.isBlank()) {
            response.setStatus(401);
            response.getWriter().write("Unauthorized: token missing");
            response.getWriter().flush();
            return false;
        }

        Claims claims = null;
        try {
            claims = JwtUtils.parseToken(token);

            // 把 claims 放到 request 中，Controller 可随时取用
            request.setAttribute("id", claims.get("id"));
            request.setAttribute("root_priv",claims.get("root_priv"));
        } catch (Exception e) {
            response.setStatus(401);
            response.getWriter().write("Unauthorized: token invalid");
            response.getWriter().flush();
            return false;
        }

        // 权限验证
        if (handler instanceof HandlerMethod hm) {

            // 超级管理员鉴权
            if (hm.getMethodAnnotation(RequireRoot.class) != null) {
                Integer rootPriv = (Integer) claims.get("root_priv");
                if (rootPriv == null || rootPriv != 1) {
                    response.setStatus(403);
                    response.getWriter().write("Forbidden: root privilege required");
                    response.getWriter().flush();
                    return false;
                }
            }

            // 资源归属
            if (hm.getMethodAnnotation(RequireSelf.class) != null) {
                Integer loginId = (Integer) request.getAttribute("id");

            }
        }

        return true;
    }
}
