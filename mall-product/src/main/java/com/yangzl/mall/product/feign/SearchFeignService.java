package com.yangzl.mall.product.feign;

import com.yangzl.common.to.es.SkuEsTo;
import com.yangzl.common.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/17
 *
 * 检索服务远程调用声明
 */
// @FeignClient("mall-search")

public interface SearchFeignService {

    /**
     * 上架到检索系统
     *
     * @param tos tos
     * @return R
     */
    @PostMapping("/search/product/save")
    R up(@RequestBody List<SkuEsTo> tos);
}
