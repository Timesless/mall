package com.yangzl.mall.order.dao;

import com.yangzl.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 *
 * @author yangzl
 * @date 2020/11/19 21:31:40
 * @desc
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

}
