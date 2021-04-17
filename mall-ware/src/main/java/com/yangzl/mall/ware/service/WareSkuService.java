package com.yangzl.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author yangzl
 * @date 2020/11/19 21:28:43
 * @desc
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    /**
     * 分页查询
     *
     * @param params params
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取每个 sku 的库存数
     *
     * @param skuIds skuids
     * @return list
     */
    List<Long> getStockBySkuIds(List<Long> skuIds);
}

