package com.yangzl.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author yangzl
 * @date 2020/11/19 21:31:40
 * @desc
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

