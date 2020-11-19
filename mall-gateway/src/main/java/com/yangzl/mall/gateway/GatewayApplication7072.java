package com.yangzl.mall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:45
 * @desc 统一网关
 *
 *  引入依赖 org.springframework.cloud:spring-cloud-starter-gateway
 *
 */

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication7072 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication7072.class, args);
    }
}
