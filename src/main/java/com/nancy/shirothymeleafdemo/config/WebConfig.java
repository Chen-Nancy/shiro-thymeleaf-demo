package com.nancy.shirothymeleafdemo.config;

import com.nancy.shirothymeleafdemo.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author chen
 * @date 2020/5/31 23:32
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Resource
    private SessionInterceptor sessionInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
        WebConfig.super.addResourceHandlers(registry);
    }
}
