package com.goinghugh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

/**
 * 启动类
 *
 * @author yongxu wang
 * @date 2019-05-16 10:01
 **/
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class HttpEchoApplication {
    public static void main(String[] args) {
        SpringApplication.run(HttpEchoApplication.class, args);
    }
}
