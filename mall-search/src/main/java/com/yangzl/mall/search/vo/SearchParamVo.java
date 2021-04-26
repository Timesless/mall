package com.yangzl.mall.search.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/19
 * @desc 检索 vo
 *
 *  检索条件：
 *      1. 关键字
 *      2. 三级分类 id
 *      3. 全文检索：skuTitle
 *      4. 排序：saleCount, hotScore, skuPrice
 *      5. 过滤：hasStock, skuPrice 区间, brandId, catalog3Id, attrs
 *      6. 聚合 attrs
 *
 *  栗子：
 *      keyword=小米&sort=saleCount_desc/asc&hasStock=0/1&skuPrice=400_1900
 *          &brandId=1&catalog3Id=1&attrs=1_3G:4G:5G&attrs=2_A12仿生:A12X&attrs=4_120HZ
 *  多个属性以 冒号 分割
 *  区间以 下划线 分割
 */
@Getter
@Setter
public class SearchParamVo {

    private String keyword;
    private Long catalog3Id;

    private String sort;

    private Integer hasStock;
    private String skuPrice;
    private List<Long> brandId;
    private List<String> attrs;
    private Integer pageNum;
}
