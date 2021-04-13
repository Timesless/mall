package com.yangzl.mall.product.feign;

import com.yangzl.common.to.SkuReductionTo;
import com.yangzl.common.to.SpuBoundTo;
import com.yangzl.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc
 *
 *      SpringCloud rpc 调用逻辑：
 *          1. 将请求实体转换为 JSON 串
 *          2. 从注册中心中找到 mall-coupon 服务，給 /coupon/spubounds/save 发送请求，携带 JSON 数据
 *          3. mall-coupon 服务 Spring MVC 处理请求，将请求 JSON 数据 封装为 SpuBoundEntity「属性能对应，就不会异常」
 *      只要 JSON 格式兼容，实体类不必一模一样
 */
@FeignClient("mall-coupon")
public interface CouponFeignService {

    /**
     * 购买商品赠送积分保存
     *
     * @param boundTo 积分 TO
     * @return R r
     */
    @PostMapping("coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo boundTo);

    /**
     * sku reduction
     *
     * @param skuTo skuto
     * @return R r
     */
    @PostMapping("coupon/spubounds/saveReduction")
    R saveSkuReduction(@RequestBody SkuReductionTo skuTo);
}
