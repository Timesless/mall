package com.yangzl.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:45
 * @desc 全文检索系统
 */

@EnableDiscoveryClient
@SpringBootApplication
public class SearchApplication7077 {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication7077.class, args);
    }
}
