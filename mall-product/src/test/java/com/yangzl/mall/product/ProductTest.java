package com.yangzl.mall.product;

import com.yangzl.mall.product.entity.BrandEntity;
import com.yangzl.mall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

/**
 * @author yangzl
 * @date 2020/11/19 21:47
 * @desc
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProductApplication7076.class)
public class ProductTest {

    Logger logger = LoggerFactory.getLogger(ProductTest.class);

    @Resource
    private BrandService brandService;

    @Test
    public void testAdd() {
        BrandEntity brand = new BrandEntity();
        brand.setName("Apple");
        brand.setDescript("Apple.INC");
        brandService.save(brand);
        logger.info("保存成功");
    }

    @Test
    public void testQuery() {
        logger.info(brandService.getById(1).toString());
    }
}
