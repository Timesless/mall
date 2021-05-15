package com.yangzl.mall.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 第三方服务系统
 *      oss
 *      sms
 *      weibo
 *
 * @author yangzl
 * @date 2021/4/6
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ThirdApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdApplication.class);
    }
}
