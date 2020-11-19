package com.yangzl.mall.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:44
 * @desc 购物车系统
 */

@EnableDiscoveryClient
@SpringBootApplication
public class CartApplication7070 {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication7070.class, args);
    }
}
