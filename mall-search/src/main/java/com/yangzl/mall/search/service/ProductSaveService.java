package com.yangzl.mall.search.service;

import com.yangzl.common.to.es.SkuEsTo;

import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/17
 * @desc
 */
public interface ProductSaveService {

    /**
     * 商品上架到检索系统
     *
     * @param tos tos
     */
    boolean up(List<SkuEsTo> tos);
}
