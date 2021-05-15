package com.yangzl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.product.entity.SkuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * sku图片
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    /**
     * 分页查询
     *
     * @param params param
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过 skuId 查询所有图片
     *
     * @param skuId skuid
     * @return images
     */
    List<SkuImagesEntity> getImagesBySkuId(Long skuId);
}

