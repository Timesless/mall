package com.yangzl.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.yangzl.common.to.es.SkuEsTo;
import com.yangzl.mall.search.consts.EsConstant;
import com.yangzl.mall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangzl
 * @date 2021/4/17
 * @desc
 */
@Slf4j
@Service
public class ProductSaveServiceImpl implements ProductSaveService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean up(List<SkuEsTo> tos) {
        BulkRequest bulk = new BulkRequest();
        for (SkuEsTo to : tos) {
            IndexRequest index = new IndexRequest(EsConstant.PRODUCT_INDEX).id(to.getSkuId().toString());
            index.source(JSON.toJSONString(to), XContentType.JSON);
            bulk.add(index);
        }
        try {
            BulkResponse response = restHighLevelClient.bulk(bulk, RequestOptions.DEFAULT);
            boolean flag = response.hasFailures();
            List<String> ids = Arrays.stream(response.getItems()).map(BulkItemResponse::getId).collect(Collectors.toList());
            log.info("......商品上架成功：{}", ids);

            return flag;
        } catch (Exception e) {
            log.error("......商品上架失败，异常原因{}", e.getMessage());
        }

        return false;
    }
}
