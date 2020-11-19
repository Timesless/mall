package com.yangzl.mall.product;

import com.yangzl.mall.product.dao.AttrDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:38
 * @desc 商品系统
 */

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackageClasses = AttrDao.class)
public class BrandTest {

    public static void main(String[] args) {
        SpringApplication.run(BrandTest.class, args);
    }
}
