package com.wanwan.springboot.config;

import com.wanwan.springboot.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author：玩玩
 * @since：2024/2/2 12:07
 * @description:
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                //拦截所有请求，通过判断token来决定是否需要登陆
                .addPathPatterns("/**")
                .excludePathPatterns(
                        // 登录注册放开
                        // "/api/users/login",
                        // "/api/users/register",

                        // 静态资源放开
                        "/api/**/export",
                        "/api/**/import",
                        "/api/swagger-resources/**",
                        "/api/webjars/**",
                        "/api/v2/**",
                        "/api/swagger-ui.html/**"

                        // // 主页可用轮播图
                        // "/api/home/slider",
                        //
                        // // 文章页面
                        // "/api/articles/page",
                        // "/api/articles/hots",
                        // "/api/articles/relations",
                        // "/api/articles/statistics",
                        // "/api/articles/page",
                        //
                        // // 文章评论
                        // "/api/comments/**",
                        //
                        // // 留言板
                        // "/api/leavewords/**",
                        //
                        //
                        // "/api/roles",
                        // "/api/categories/**"

                );

    }
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

}
