package com.goinghugh.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置
 *
 * @author yongxu wang
 * @date 2019-05-20 14:38
 **/
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Autowired
    private TraceInterceptor traceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(traceInterceptor).order(0);
        registry.addInterceptor(sessionInterceptor);
    }


}
