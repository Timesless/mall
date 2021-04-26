package com.yangzl.mall.product.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yangzl
 * @date 2021/4/19
 * @desc
 */
@Controller
public class ItemController {

    @GetMapping("/{skuId}.html")
    public String skuDetail(@PathVariable Long skuId) {

        return "item";
    }
}
