package com.yangzl.mall.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 业务池测试
 *
 * @author yangzl
 * @date 2021/5/15
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProductApplication.class)
public class ThreadPoolTest {

    @Resource
    private ThreadPoolExecutor threadPoolExecutorIo;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Test
    public void testThreadPool() {
        System.out.println(threadPoolExecutor);
        System.out.println("====================");
        System.out.println(threadPoolExecutorIo);
    }

}
