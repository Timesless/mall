package com.yangzl.mall.product.dao;

import com.yangzl.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

}
