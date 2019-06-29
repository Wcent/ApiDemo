package org.cent.ApiDemo.configuration;

import org.cent.ApiDemo.interceptor.CustomHandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器注册配置
 * @author Vincent
 * @version 1.0 2019/6/23
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterceptorConfiguration.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LOGGER.info("#######################注册拦截器#######################");
        registry.addInterceptor(handlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/api/**");
    }

    @Bean
    public static HandlerInterceptor handlerInterceptor() {
        return new CustomHandlerInterceptor();
    }
}
