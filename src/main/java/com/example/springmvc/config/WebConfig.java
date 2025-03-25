package com.example.springmvc.config;

import com.example.springmvc.interceptor.JwtAuthInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configurable
public class WebConfig implements WebMvcConfigurer {
    private final JwtAuthInterceptor jwtAuthInterceptor;

    public WebConfig(JwtAuthInterceptor jwtAuthInterceptor) {
        this.jwtAuthInterceptor = jwtAuthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthInterceptor)
                .addPathPatterns("/api/posts/**", "/api/images/**") // 인증 필요한 URL
                .excludePathPatterns("/api/posts", "/api/posts/**") // GET 요청 허용용 예외 (선택 사항)
                .excludePathPatterns("/api/users/register", "/api/users/login"); // 로그인/회원가입은 인증 제외
    }
}
