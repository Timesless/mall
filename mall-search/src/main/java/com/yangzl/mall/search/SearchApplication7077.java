package com.yangzl.mall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangzl
 * @date 2020/11/9 14:45
 * @desc 全文检索系统
 *
 * 1. 日志检索
 * 2. 商品检索
 *    冗余数据方便检索，所有冗余字段 index = false，doc_value「true 可聚合」: false
 *    {
 *        skuid: long
 *        spuid: keyword
 *        # 唯一分词检索的字段
 *        skutitle: {type: text, analyzer: ik_smart}
 *        price: keyword
 *        salecount: long
 *        skudefaultImg: {type: keyword, index: false, doc_values: false}
 *        hasStock: boolean
 *        catelogId: long
 *        catelogName: {keyword, index: false, doc_values: false}
 *        brandId: long
 *        brandName: {keyword, index: false, doc_values: false}
 *        attrs: {
 *            type: nested
 *            properties: attrId: {type: long},
 *            attrName: {type: keyword, index: false, doc_values: false},
 *            attrValue: {type: keyword}
 *        }
 *    }
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SearchApplication7077 {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication7077.class, args);
    }
}
