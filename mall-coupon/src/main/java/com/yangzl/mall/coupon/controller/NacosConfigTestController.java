package com.yangzl.mall.coupon.controller;

import com.yangzl.common.utils.R;
import com.yangzl.mall.coupon.entity.CouponEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author yangzl
 * @date 2020/11/22 23:12
 * @desc do test
 */
@RefreshScope
@RestController
@RequestMapping("coupon/test/")
public class NacosConfigTestController {

    @Value("${coupon.user.name}")
    private String name;
    @Value("${coupon.user.age}")
    private int age;

    /**
     * 2020/11/22 OpenFeign远程调用测试
     *
     * @param () 无参
     * @return R
     */
    @GetMapping("coupons")
    public R getCoupons() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("满100 - 40");
        couponEntity.setId(1024L);
        return R.ok().put("coupon", Collections.singletonList(couponEntity));
    }

    @GetMapping("config")
    public String getConfig() {
        return "name = " + name + ", age = " + age;
    }
}
