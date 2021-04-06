package com.yangzl.mall.member.feign;

import com.yangzl.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yangzl
 * @date 2020/11/22 21:57
 * @desc feign 使用
 *      1. 引入 open-feign
 *      2. 编写接口，指定要调用哪个模块的服务（nacos中以 spring.application.name 区分）
 *      3. 编写方法（同 controller 写法）
 *      4. @EnableFeignClients
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
