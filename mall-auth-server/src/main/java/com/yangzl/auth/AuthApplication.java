package com.yangzl.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 统一认证服务
 *
 * @author yangzl
 * @date 2021/5/15
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthApplication.class);
    }

}
