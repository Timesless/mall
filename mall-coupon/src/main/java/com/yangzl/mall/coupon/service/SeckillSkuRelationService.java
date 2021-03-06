package com.yangzl.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.coupon.entity.SeckillSkuRelationEntity;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author yangzl
 * @date 2020/11/19 21:30:15
 * @desc
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

