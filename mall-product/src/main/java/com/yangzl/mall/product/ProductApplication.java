package com.yangzl.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yangzl
 * @date 2020/11/9 14:38
 *
 * 商品系统
 *
 * 分布式缓存一致性保障：
 *     1. 双写「修改数据后，将新数据写入缓存」
 *      node1 先修改数据后，写入缓存卡顿，耗时 2s
 *      node2 后修改数据后，写入缓存耗时 1s，则此时缓存数据依然为 node1 先写入的数据「脏数据」
 *     2. 失效「修改数据后，将缓存删除」
 *      node1 先修改数据后，删除缓存
 *      node2 后修改数据，卡顿 3s 后删除缓存
 *      node3 读取数据，在 3s 之间，此时读到 node1 修改的数据，再放入缓存「脏数据」
 *
 *     2.1 延时双删「修改数据后，将缓存删除，延时一段时间后再删除」
 *
 *
 *  1. redisson
 *      1. 导入依赖 org.redisson:redisson.spring-boot-starter
 *      2. 自动配置类已经注入了 RedisTemplate 和 StringRedisTemplate
 *
 *  2. redis 引入
 *      1. 导入依赖 org.springframework.boot:spring-boot-starter-data-redis
 *      2. yaml 配置 redis 连接信息 host, port, passwd
 *
 *  3. SpringCache
 *      1. 导入依赖 org.springframework.boot:spring-boot-starter-cache
 *      2. Interface CacheManager
 *          RedisCacheManager
 *          ConcurrentMapCacheManager
 *
 *      CacheConfiguration
 *      RedisCacheConfiguration
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.yangzl.mall.product.dao")
@EnableFeignClients(basePackages = "com.yangzl.mall.product.feign")
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
