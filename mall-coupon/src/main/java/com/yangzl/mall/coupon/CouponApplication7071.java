package com.yangzl.mall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yangzl
 * @date 2020/11/9 14:24
 * @desc 优惠系统
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.yangzl.mall.coupon.dao")
public class CouponApplication7071 {

    public static void main(String[] args) {
        SpringApplication.run(CouponApplication7071.class, args);
    }
}
