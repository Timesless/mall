package com.yangzl.mall.product.controller;

import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.R;
import com.yangzl.mall.product.service.AttrService;
import com.yangzl.mall.product.vo.AttrRespVO;
import com.yangzl.mall.product.vo.AttrVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 商品属性
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Resource
    private AttrService attrService;

    @GetMapping("/base/list/{catelogId}")
    public R baseCatelogList(@RequestParam Map<String, Object> params, @PathVariable Long catelogId){
        PageUtils page = attrService.queryAttrBaseInfoPage(params, catelogId);

        return R.ok().put("page", page);
    }

    @GetMapping("/sale/list/{catelogId}")
    public R saleCatelogList(@RequestParam Map<String, Object> params, @PathVariable Long catelogId){
        PageUtils page = attrService.queryAttrSaleInfoPage(params, catelogId);

        return R.ok().put("page", page);
    }

    // =========

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
		// AttrEntity attr = attrService.getById(attrId);
        AttrRespVO attr = attrService.getAttrInfoById(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVO attr){
		// attrService.save(attr);
        attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVO attr){
		// attrService.updateById(attr);
        attrService.updateCascade(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
