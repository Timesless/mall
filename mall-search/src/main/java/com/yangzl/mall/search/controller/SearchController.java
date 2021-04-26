package com.yangzl.mall.search.controller;

import com.yangzl.mall.search.service.SearchService;
import com.yangzl.mall.search.vo.SearchParamVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @author yangzl
 * @date 2021/4/19
 * @desc
 */
@Controller
public class SearchController {

    @Resource
    private SearchService searchService;

    /**
     * 搜索服务 - 首页
     *
     * @return str
     */
    @GetMapping("/search.html")
    public String index(SearchParamVo searchParamVo) {

        Object rs = searchService.search(searchParamVo);

        return "search";
    }

}
