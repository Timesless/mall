package com.yangzl.mall.product.web;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yangzl
 * @date 2021/4/18
 * @desc
 */
@Slf4j
@RestController
public class RedissonTestController {

    private static final String REDIS_REENTRANTLOCK_KEY = "redis-reentrantlock";
    private static final String REDIS_RWLOCK_KEY = "redis-rwlock";
    private static final String REDIS_SEMAPHORE_KEY = "redis-semaphore";
    private static final String REDIS_COUNTDOWNLATCH_KEY = "redis-countdownlatch";

    @Resource
    private Redisson redisson;

    /**
     * ReentrantLock 测试
     *
     * @return str
     */
    @GetMapping("redisson/reentrantLock")
    public String reentrantLock() {
        RLock lock = redisson.getLock(REDIS_REENTRANTLOCK_KEY);
        lock.lock();
        try {
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println( System.getProperty("server.port") + " server 获取到锁 ");
        } finally {
            lock.unlock();
        }

        return "reentrantlock";
    }

    /**
     * 获取信号量
     *
     * @return str
     */
    @GetMapping("/redisson/park")
    public String park() {
        RSemaphore semaphore = redisson.getSemaphore(REDIS_SEMAPHORE_KEY);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        return "park success";
    }

    /**
     * 释放信号量
     *
     * @return str
     */
    @GetMapping("/redisson/unpark")
    public String unpark() {
        RSemaphore semaphore = redisson.getSemaphore(REDIS_SEMAPHORE_KEY);
        semaphore.release();

        return "unpark success";
    }

    /**
     * 读写锁 - 读锁
     *  读读共享
     *
     * @return str
     */
    @GetMapping("/redisso/readLock")
    public String readLock() {
        RLock readLock = redisson.getReadWriteLock(REDIS_RWLOCK_KEY).readLock();
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 读取数据......");
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        } finally {
            readLock.unlock();
        }

        return "read success";
    }

    /**
     * 读写锁 - 写锁
     *  写写互斥
     *  读写互斥
     *
     * @return str
     */
    @GetMapping("/redisson/writeLock")
    public String writeLock() {
        RLock writeLock = redisson.getReadWriteLock(REDIS_RWLOCK_KEY).writeLock();
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 写入数据......");
            try { TimeUnit.SECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        } finally {
            writeLock.unlock();
        }

        return "write success";
    }

    @GetMapping("/redisson/countdownLatch")
    public String countdownLatch() {
        RCountDownLatch countDownLatch = redisson.getCountDownLatch(REDIS_COUNTDOWNLATCH_KEY);
        countDownLatch.trySetCount(5);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        return "放假";
    }

    /**
     * count donw
     *
     * @param id id
     * @return str
     */
    @GetMapping("/redisson/countdown/{id}")
    public String countdown(@PathVariable int id) {
        RCountDownLatch countDownLatch = redisson.getCountDownLatch(REDIS_COUNTDOWNLATCH_KEY);
        countDownLatch.countDown();

        return id + " count down";
    }

}
