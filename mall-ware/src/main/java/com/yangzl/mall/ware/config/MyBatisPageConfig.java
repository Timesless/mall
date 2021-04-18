package com.yangzl.mall.ware.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc 分页插件「拦截器配置」
 */
@Configuration
@MapperScan("com.yangzl.mall.ware.dao")
public class MyBatisPageConfig {

    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor interceptor = new PaginationInnerInterceptor();
        interceptor.setMaxLimit(200L);
        interceptor.setOverflow(true);

        return interceptor;
    }
}
