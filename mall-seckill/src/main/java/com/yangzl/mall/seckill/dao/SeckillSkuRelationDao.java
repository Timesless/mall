package com.yangzl.mall.seckill.dao;

import com.yangzl.mall.seckill.entity.SeckillSkuRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动商品关联
 *
 * @author yangzl
 * @date 2020/11/19 21:30:15
 * @desc
 */
@Mapper
public interface SeckillSkuRelationDao extends BaseMapper<SeckillSkuRelationEntity> {

}