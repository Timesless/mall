package com.yangzl.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangzl.mall.product.entity.AttrGroupEntity;
import com.yangzl.mall.product.vo.SpuItemAttrGroupVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性分组
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

    /**
     * 查询
     *
     * @param spuId spudi
     * @param catalogId 三级分类id
     * @return list
     */
    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId,
                                                          @Param("catalogId") Long catalogId);
}
