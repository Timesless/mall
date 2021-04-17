package com.yangzl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.product.entity.SkuInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    /**
     * 分页查询
     *
     * @param params param
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 按条件分页查询
     *
     * @param params param
     * @return page
     */
    PageUtils queryByCondition(Map<String, Object> params);

    /**
     * 根据 spuId 查询所有 sku
     *
     * @param spuId spuid
     */
    List<SkuInfoEntity> getSkusBySpuId(Long spuId);
}

