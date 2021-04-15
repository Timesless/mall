package com.yangzl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
public interface CategoryService extends IService<CategoryEntity> {

    /**
     * 分页查询
     *
     * @param params params
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 以树形结构封装分类
     *
     * @return list
     */
    List<CategoryEntity> listForTree();

    /**
     * 级联更新 category「保证其他表冗余字段更新」
     *
     * @param category category
     */
    void updateCascade(CategoryEntity category);

    /**
     * 递归查询 获取级联选择器的级联id，以数组返回
     *
     * @param catelogId catelogId
     * @return long[]
     */
    Long[] findCategoryPath(Long catelogId);
}

