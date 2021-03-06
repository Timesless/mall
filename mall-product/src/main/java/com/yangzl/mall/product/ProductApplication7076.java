package com.yangzl.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yangzl
 * @date 2020/11/9 14:38
 * @desc 商品系统
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.yangzl.mall.product.dao")
@EnableFeignClients(basePackages = "com.yangzl.mall.product.feign")
public class ProductApplication7076 {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "7076");
        SpringApplication.run(ProductApplication7076.class, args);
    }
}
