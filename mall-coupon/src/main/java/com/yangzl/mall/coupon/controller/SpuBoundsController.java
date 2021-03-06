package com.yangzl.mall.coupon.controller;

import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.R;
import com.yangzl.mall.coupon.entity.SpuBoundsEntity;
import com.yangzl.mall.coupon.service.SpuBoundsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 商品spu积分设置
 *
 * @author yangzl
 * @date 2020/11/19 21:30:15
 * @desc
 */
@RestController
@RequestMapping("coupon/spubounds")
public class SpuBoundsController {
    @Resource
    private SpuBoundsService spuBoundsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuBoundsService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SpuBoundsEntity spuBounds = spuBoundsService.getById(id);

        return R.ok().put("spuBounds", spuBounds);
    }

    /**
     * 保存
     * 被 rpc 调用
     */
    @PostMapping("/save")
    public R save(@RequestBody SpuBoundsEntity spuBounds){
    // public R save(@RequestBody SpuBoundTo spuBounds){
		spuBoundsService.save(spuBounds);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SpuBoundsEntity spuBounds){
		spuBoundsService.updateById(spuBounds);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		spuBoundsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
