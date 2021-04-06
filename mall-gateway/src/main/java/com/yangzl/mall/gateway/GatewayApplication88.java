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
 *
 *  1. Glossary「词汇表」
 *         Route
 *         Predicate
 *         Filter
 *  2. How it works
 *          1. Gateway Client
 *          2. Gateway Handler Mapping
 *          3. Gateway Web Handler
 *          4. Filters
 *          5. Proxied Service
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication88 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication88.class, args);
    }
}
