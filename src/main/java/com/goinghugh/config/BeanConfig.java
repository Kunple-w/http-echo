package com.goinghugh.config;

import com.goinghugh.multipart.CustomCommonsMultipartResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.MultipartResolver;

/**
 * <p></p>
 *
 * @author wangyongxu
 * @date 2019/6/17 11:37
 */
@Configuration
public class BeanConfig {

    @Bean
    @Order(1)
    public MultipartResolver multipartResolver() {
        return new CustomCommonsMultipartResolver();
    }
}
