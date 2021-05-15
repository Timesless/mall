package com.yangzl.mall.product.feign;

import com.yangzl.common.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/17
 * @desc
 */
// @FeignClient("mall-ware")

public interface WareFeignService {

    /**
     * 返回每个 sku 的库存量
     *
     * @param skuIds skuIds
     * @return R
     */
    @PostMapping("/ware/waresku/hasstock")
    R<List<Long>> skusHasStock(@RequestBody List<Long> skuIds);

}
