package com.yangzl.mall.ware.dao;

import com.yangzl.mall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存
 *
 * @author yangzl
 * @date 2020/11/19 21:28:43
 * @desc
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    /**
     * 查询每个 sku 的库存
     *
     * @param skuIds skuIds
     * @return list
     */
    List<Long> getStockBySkuIds(@Param("skuIds") List<Long> skuIds);
}
