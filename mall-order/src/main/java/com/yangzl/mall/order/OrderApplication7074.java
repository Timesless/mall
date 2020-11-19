package com.yangzl.mall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:37
 * @desc 订单系统
 */

@EnableDiscoveryClient
@SpringBootApplication
public class OrderApplication7074 {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication7074.class, args);
    }
}
