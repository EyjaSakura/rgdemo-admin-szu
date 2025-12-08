package com.szu.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/admin/**")     // 拦截所有后台管理员接口
                .excludePathPatterns("/admin/user/login-admin"); // 登录接口放行
    }
}
