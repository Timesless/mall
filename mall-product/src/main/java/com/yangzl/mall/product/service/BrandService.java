package com.yangzl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

