package com.yangzl.mall.product.dao;

import com.yangzl.mall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author yangzl
 * @date 2020/11/19 20:27:09
 * @desc
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    /**
     * 批量删除
     *
     * @param list list
     */
    void deleteBatchRelation(@Param("list") List<AttrAttrgroupRelationEntity> list);
}
