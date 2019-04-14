package com.zelin.config;

import com.zelin.interceptor.AuthticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/13 11:50
 */
@Configuration
public class MyConfiguration implements WebMvcConfigurer {
    @Autowired
    private AuthticationInterceptor authticationInterceptor;
    //注册拦截器（springboot拦截器与springmvc不一样，还会拦截静态资源 ）
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authticationInterceptor).addPathPatterns("**")
                .excludePathPatterns("/static/**");

    }
    /**
     * 表示这些配置的表示静态文件所处路径， 不用拦截
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/templates/**")
//                .addResourceLocations("classpath:/templates/");
//
//    }
}
