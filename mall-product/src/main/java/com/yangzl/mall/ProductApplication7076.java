package com.yangzl.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:38
 * @desc 商品系统
 */

@EnableDiscoveryClient
@SpringBootApplication
public class ProductApplication7076 {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication7076.class, args);
    }
}
