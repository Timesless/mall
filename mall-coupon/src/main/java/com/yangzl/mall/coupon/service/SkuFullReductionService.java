package com.yangzl.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.to.SkuReductionTo;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author yangzl
 * @date 2020/11/19 21:30:15
 * @desc
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    /**
     * 分页查询
     *
     * @param params param
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * rpc 调用保存
     *
     * @param reductionTo to
     */
    void saveReduction(SkuReductionTo reductionTo);
}

