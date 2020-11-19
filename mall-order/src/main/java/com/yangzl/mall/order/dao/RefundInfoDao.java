package com.yangzl.mall.order.dao;

import com.yangzl.mall.order.entity.RefundInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款信息
 *
 * @author yangzl
 * @date 2020/11/19 21:31:40
 * @desc
 */
@Mapper
public interface RefundInfoDao extends BaseMapper<RefundInfoEntity> {

}
