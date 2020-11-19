package com.yangzl.mall.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:46
 * @desc 秒杀系统
 */

@EnableDiscoveryClient
@SpringBootApplication
public class SeckillApplication7078 {

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication7078.class, args);
    }
}
