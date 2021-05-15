package com.yangzl.mall.product.web;

import com.yangzl.mall.product.service.SkuInfoService;
import com.yangzl.mall.product.vo.SkuItemVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author yangzl
 * @date 2021/4/19
 */
@Controller
public class ItemController {

    @Resource
    private SkuInfoService skuInfoService;

    /**
     * 展示当前 sku 的信息
     *
     * @param skuId skuId
     * @return str
     */
    @GetMapping("/{skuId}.html")
    public String skuDetail(@PathVariable Long skuId, Model model) {
        SkuItemVo item = skuInfoService.item(skuId);
        model.addAttribute("item", item);

        return "item";
    }
}
