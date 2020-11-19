package com.yangzl.mall.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:38
 * @desc 库存系统
 */

@EnableDiscoveryClient
@SpringBootApplication
public class WareApplication7079 {

    public static void main(String[] args) {
        SpringApplication.run(WareApplication7079.class, args);
    }
}
