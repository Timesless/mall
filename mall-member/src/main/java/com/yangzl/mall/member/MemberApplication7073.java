package com.yangzl.mall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yangzl
 * @date 2020/11/9 14:36
 * @desc 会员系统
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.yangzl.mall.member.dao")
@EnableFeignClients(basePackages = "com.yangzl.mall.member.feign")
public class MemberApplication7073 {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication7073.class, args);
    }
}
