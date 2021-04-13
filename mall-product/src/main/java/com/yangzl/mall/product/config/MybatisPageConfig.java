package com.yangzl.mall.product.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangzl
 * @date 2021/4/7
 * @desc MybatisPlus 分页插件（拦截 sql 添加分页）
 */
@Configuration
@MapperScan("com.yangzl.mall.product.dao")
public class MybatisPageConfig {

    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor interceptor = new PaginationInnerInterceptor();
        interceptor.setOverflow(true);
        interceptor.setMaxLimit(200L);

        return interceptor;
    }
}
