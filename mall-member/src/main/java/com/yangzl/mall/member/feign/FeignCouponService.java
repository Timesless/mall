package com.yangzl.mall.member.feign;

import com.yangzl.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yangzl
 * @date 2020/11/22 21:57
 * @desc
 */

@FeignClient("mall-coupon")
public interface FeignCouponService {


    /**
     * 2020/11/22 测试Feign远程调用
     *
	 * @param () 无参
     * @return R
     */
    @GetMapping("/coupon/test/coupons")
    R getCoupons();
}
