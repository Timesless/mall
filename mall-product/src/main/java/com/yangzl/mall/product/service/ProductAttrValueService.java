package com.yangzl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    /**
     * 分页查询
     *
     * @param params param
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据 attrIds 筛选出可以被检索的属性
     *
     * @param spuId spuId
     * @return list
     */
    List<ProductAttrValueEntity> selectSearchAttrIds(Long spuId);
}

