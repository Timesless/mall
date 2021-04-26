package com.yangzl.mall.product.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * @author yangzl
 * @date 2021/4/18
 * @desc
 *  分布式缓存一致性保障：
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
 *
 *     CacheProperties 只是一个普通类，并没有放入到 Spring 容器中
 *     如果要注入为 Spring Bean，则需要一个对应的的 @EnableConfigurationProperties
 *          1. @Resource 注入
 *          2. 直接在方法参数注入
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
public class RedisConfig {

    /**
     * redis 缓存配置
     *      <b>如果是自定义的 bean 则 redis 不会使用配置文件中的 redisproperties</b>
     *      依葫芦画瓢，参照 RedisCacheConfiguration 写的
     *  1. 设置序列化为 json「通用格式便于跨语言」
     *
     * @return RedisCacheConfiguration
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
            .entryTtl(Duration.ofMinutes(30));

        /*
         * 使用配置文件的配置「yaml, properties」
         */
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }

        return config;
    }
}
