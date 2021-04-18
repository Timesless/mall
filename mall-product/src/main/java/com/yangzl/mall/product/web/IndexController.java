package com.yangzl.mall.product.web;

import com.yangzl.mall.product.entity.CategoryEntity;
import com.yangzl.mall.product.service.CategoryService;
import com.yangzl.mall.product.vo.Category2Vo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yangzl
 * @date 2021/4/17
 * @desc 1. 首页请求
 *       2. jmeter 测试请求
 */
@Controller
public class IndexController {

    @Resource
    private CategoryService categoryService;

    @GetMapping({"/", "/index.html"})
    public String indexPage(Model model) {
        /*
         * 1. 查询一级分类
         *
         */
        List<CategoryEntity> level1 = categoryService.getLevel1Categories();
        model.addAttribute("category", level1);

        // 视图解析：prefix + "index" + suffix
        // classpath: /templates/index.html
        return "index";
    }

    /**
     * 获取分类数据
     *
     * @return obj
     */
    @ResponseBody
    @GetMapping("/index/catalog")
    public Map<String, List<Category2Vo>> getCatalogJson() {

        return categoryService.getCatalogJson();
    }

    /**
     * jmeter 压力测试最简单调用
     *  1. 直连测试
     *  2. + 网关
     *  3. + nginx + 网关
     *
     * @return str
     */
    @ResponseBody
    @GetMapping("/hello")
    public String hello() {

        return "hello";
    }

}
