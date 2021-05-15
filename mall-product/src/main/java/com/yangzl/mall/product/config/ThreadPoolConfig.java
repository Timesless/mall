package com.yangzl.mall.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 添加业务线程池
 *
 * @author yangzl
 * @date 2021/5/15
 */
// @EnableConfigurationProperties(ThreadPoolConfigProperties.class)

@Configuration
public class ThreadPoolConfig {

    @Bean("threadPoolExecutorIo")
    public ThreadPoolExecutor threadPoolExecutorIo() {

        return new ThreadPoolExecutor(
            16,
            64,
            45,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(256),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());
    }

    @Bean("threadPoolExecutor")
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties pool) {

        return new ThreadPoolExecutor(
            pool.getCoreSize(),
            pool.getMaxSize(),
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(pool.getQueueCapacity()),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
