package com.yangzl.auth.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * view controller
 *
 * @author yangzl
 * @date 2021/5/15
 */
public class WebControllerConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/reg.html").setViewName("reg");
        registry.addViewController("/login.html").setViewName("login");

        WebMvcConfigurer.super.addViewControllers(registry);
    }
}
