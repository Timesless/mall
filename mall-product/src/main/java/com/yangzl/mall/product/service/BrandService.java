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

    /**
     * 分页查询
     *
     * @param params params
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 级联更新品牌，需要保证冗余字段的一致
     *
     * @param brand brand
     */
    void updateCascade(BrandEntity brand);
}

