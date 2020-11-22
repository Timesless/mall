package com.yangzl.mall.member.controller;

import com.yangzl.common.utils.R;
import com.yangzl.mall.member.entity.MemberEntity;
import com.yangzl.mall.member.feign.FeignCouponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangzl
 * @date 2020/11/22 23:10
 * @desc 测试
 */

@RestController
@RequestMapping("user/test/")
public class TestController {

    @Resource
    private FeignCouponService feignCouponService;

    @GetMapping("feign")
    public R testFeignCall() {
        R r = feignCouponService.getCoupons();
        MemberEntity member = new MemberEntity();
        member.setNickname("yangzl");
        member.setCity("成都");

        return R.ok().put("meber", member).put("coupon", r.get("coupon"));
    }

}
