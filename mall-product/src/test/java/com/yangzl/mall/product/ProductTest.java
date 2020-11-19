package com.yangzl.mall.product;

import com.yangzl.mall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

/**
 * @author yangzl
 * @date 2020/11/19 21:47
 * @desc
 */


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductTest {

    @Resource
    private BrandService brandService;

    @Test
    public void test1() {
        System.out.println(brandService.getById(1));
    }
}
