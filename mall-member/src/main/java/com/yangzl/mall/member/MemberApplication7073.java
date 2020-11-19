package com.yangzl.mall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:36
 * @desc 会员系统
 */

@EnableDiscoveryClient
@SpringBootApplication
public class MemberApplication7073 {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication7073.class, args);
    }
}
