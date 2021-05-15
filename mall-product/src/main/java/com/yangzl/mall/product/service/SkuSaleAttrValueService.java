package com.yangzl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.product.entity.SkuSaleAttrValueEntity;
import com.yangzl.mall.product.vo.SkuItemSaleAttrVo;

import java.util.List;
import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    /**
     * 分页查询
     *
     * @param params param
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * spu 所有销售属性「sku」组合
     *
     * @param spuId spuid
     * @return list
     */
    List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(Long spuId);
}

