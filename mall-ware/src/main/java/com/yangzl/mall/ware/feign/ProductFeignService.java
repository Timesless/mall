package com.yangzl.mall.ware.feign;

import com.yangzl.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc feign 远程调用
 *
 *      有两种方式调用
 *          1. 所有请求都通过 gateway「网关」
 *              FeignClient("mall-gateway")
 *              /api/product/skuinfo/info/{skuId}
 *          2. 直接请求对应的微服务
 *              FeignClient("mall-product")
 *              /product/skuinfo/info/{skuId}
 */
@FeignClient("mall-product")
public interface ProductFeignService {

    /**
     * 商品查询
     *
     * @param skuId skuid
     * @return R
     */
    @RequestMapping("/product/skuinfo/info/{skuId}")
    R info(@PathVariable Long skuId);

}
