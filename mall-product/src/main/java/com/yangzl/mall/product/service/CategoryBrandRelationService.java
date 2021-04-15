package com.yangzl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.product.entity.BrandEntity;
import com.yangzl.mall.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    /**
     * 分页查询
     *
     * @param params params
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 冗余字段保存，当修改时也要做出修改
     *
     * @param categoryBrandRelation relation
     */
    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    /**
     * 查询指定分类的品牌信息
     *
     * @param catId categoryId
     * @return list
     */
    List<BrandEntity> getBrandByCatId(Long catId);
}

