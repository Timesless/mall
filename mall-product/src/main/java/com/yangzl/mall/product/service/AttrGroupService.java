package com.yangzl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    /**
     * 分页查询
     *
     * @param params params
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 按照三级分类分页查询
     * @param params params
     * @param catelogId catelogId
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params, Long catelogId);
}

