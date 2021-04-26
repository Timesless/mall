package com.yangzl.mall.search.service;

import com.yangzl.mall.search.vo.SearchParamVo;

/**
 * @author yangzl
 * @date 2021/4/19
 * @desc
 */
public interface SearchService {

    /**
     * 检索
     *
     * @param searchParamVo param
     * @return rs
     */
    Object search(SearchParamVo searchParamVo);
}
