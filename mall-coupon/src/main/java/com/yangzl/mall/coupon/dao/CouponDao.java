package com.yangzl.mall.coupon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangzl.mall.coupon.entity.CouponEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 *
 * @author yangzl
 * @date 2020/11/19 21:30:15
 * @desc
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {

}
