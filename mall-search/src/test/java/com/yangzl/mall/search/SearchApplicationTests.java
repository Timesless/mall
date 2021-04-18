package com.yangzl.mall.search;

import com.alibaba.fastjson.JSON;
import com.yangzl.mall.search.entity.User;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author yangzl
 * @date 2021/4/17
 * @desc es 整合测试
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SearchApplication7077.class)
public class SearchApplicationTests {

    @Resource
    private RestHighLevelClient restHighLevelClient;


    @Test
    public void testRestClient() {
        System.out.println(restHighLevelClient);
    }

    /**
     * 创建索引
     */
    @Test
    public void testCreateIndex() throws IOException {
        IndexRequest request = new IndexRequest("users").id("1001");
        String userJson = JSON.toJSONString(new User(1001L, "zzh"));
        request.source(userJson, XContentType.JSON);

        // 同步执行
        restHighLevelClient.index(request, RequestOptions.DEFAULT);

        // 异步执行
        restHighLevelClient.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {

            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }

    /**
     * 检索 api
     * query DSL
     */
    @Test
    public void testSearchAPI() throws IOException {
        SearchRequest request = new SearchRequest("bank");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        request.source(builder);
        // 分词检索
        builder.query(QueryBuilders.matchQuery("address", "mill"));
        // 精确匹配
        builder.query(QueryBuilders.termQuery("name", "hhz"));

        // 年龄分布
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age.keyword").size(10);
        builder.aggregation(ageAgg);

        // 年龄平均值
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        builder.aggregation(balanceAvg);

        System.out.println("构建查询条件为：" + builder);

        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        // 结果数据
        SearchHit[] results = response.getHits().getHits();
        for (SearchHit rt : results) {
            User user = JSON.parseObject(rt.getSourceAsString(), User.class);
            System.out.println(user);
        }

        // 聚合的结果数据
        Aggregations aggs = response.getAggregations();
        Terms age = aggs.get("ageAgg");
        for (Terms.Bucket bucket : age.getBuckets()) {
            System.out.println("年龄：" + bucket.getKeyAsString() + " = " + bucket.getDocCount());
        }

        Avg balance = aggs.get("balanceAvg");
        System.out.println("平均薪资：" + balance);
    }
}
