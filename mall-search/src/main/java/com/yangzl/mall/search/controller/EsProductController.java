package com.yangzl.mall.search.controller;

import com.yangzl.common.enums.ExceptionEnum;
import com.yangzl.common.to.es.SkuEsTo;
import com.yangzl.common.utils.R;
import com.yangzl.mall.search.service.ProductSaveService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/17
 * @desc 商品检索服务
 */
@RestController
@RequestMapping("/search/product")
public class EsProductController {
    @Resource
    private ProductSaveService productSaveService;

    /**
     * 商品上架到检索系统
     *
     * @param tos tos
     * @return r
     */
    @PostMapping("/save")
    public R up(@RequestBody List<SkuEsTo> tos) {
        boolean flag = productSaveService.up(tos);

        return flag ? R.ok() :
            R.error(ExceptionEnum.PRODUCT_SHELVES_EXCEPTION.getCode(), ExceptionEnum.PRODUCT_SHELVES_EXCEPTION.getMsg());
    }
}
