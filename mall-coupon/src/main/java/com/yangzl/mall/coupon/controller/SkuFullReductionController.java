package com.yangzl.mall.coupon.controller;

import com.yangzl.common.to.SkuReductionTo;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.R;
import com.yangzl.mall.coupon.entity.SkuFullReductionEntity;
import com.yangzl.mall.coupon.service.SkuFullReductionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 商品满减信息
 *
 * @author yangzl
 * @date 2020/11/19 21:30:15
 * @desc
 */
@RestController
@RequestMapping("coupon/skufullreduction")
public class SkuFullReductionController {
    @Resource
    private SkuFullReductionService skuFullReductionService;

    /**
     * rpc 调用保存
     * @param reductionTo to
     * @return R
     */
    @PostMapping("/saveReduction")
    public R saveReduction(@RequestBody SkuReductionTo reductionTo) {
        skuFullReductionService.saveReduction(reductionTo);

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuFullReductionService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SkuFullReductionEntity skuFullReduction = skuFullReductionService.getById(id);

        return R.ok().put("skuFullReduction", skuFullReduction);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SkuFullReductionEntity skuFullReduction){
		skuFullReductionService.save(skuFullReduction);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SkuFullReductionEntity skuFullReduction){
		skuFullReductionService.updateById(skuFullReduction);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		skuFullReductionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
