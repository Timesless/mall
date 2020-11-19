package com.yangzl.mall.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:45
 * @desc 支付系统，对接支付宝开放平台
 */

@EnableDiscoveryClient
@SpringBootApplication
public class PayApplication7075 {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication7075.class, args);
    }
}
