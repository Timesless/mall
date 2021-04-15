package com.yangzl.mall.product.controller;

import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.R;
import com.yangzl.mall.product.entity.BrandEntity;
import com.yangzl.mall.product.service.BrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;


/**
 * 品牌
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Resource
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存，添加参数校验
     *      1. 使用 BindingResult 判断是否有异常
     *      2. 使用 Spring 统一异常处理 ControllerAdvice
     *  public R save(@Valid @RequestBody BrandEntity brand, BindingResult result){
     *         if (result.hasErrors()) {
     *             return R.error(400, "提交参数异常").put("error", result.getAllErrors());
     *         }
     * 		brandService.save(brand);
     *
     *         return R.ok();
     *  }
     */
    @RequestMapping("/save")
    public R save(@Valid @RequestBody BrandEntity brand){
        brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BrandEntity brand){
		// brandService.updateById(brand);
        brandService.updateCascade(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
