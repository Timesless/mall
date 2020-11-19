package com.yangzl.mall.order.dao;

import com.yangzl.mall.order.entity.OrderSettingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单配置信息
 *
 * @author yangzl
 * @date 2020/11/19 21:31:40
 * @desc
 */
@Mapper
public interface OrderSettingDao extends BaseMapper<OrderSettingEntity> {

}
