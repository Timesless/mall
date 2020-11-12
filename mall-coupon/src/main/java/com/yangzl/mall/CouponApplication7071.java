package com.yangzl.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:24
 * @desc 优惠系统
 */

@EnableDiscoveryClient
@SpringBootApplication
public class CouponApplication7071 {

    public static void main(String[] args) {
        SpringApplication.run(CouponApplication7071.class, args);
    }
}
