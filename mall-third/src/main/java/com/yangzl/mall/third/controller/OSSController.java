package com.yangzl.mall.third.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yangzl
 * @date 2021/4/6
 * @desc
 */
@RestController
@RequestMapping("third/")
public class OSSController {
    @Value("alibaba.cloud.access-key")
    private String accessId;
    @Value("alibaba.cloud.secret-key")
    private String accessKey;
    @Value("alibaba.cloud.oss.endpoint")
    private String endpoint;
    @Value("alibaba.cloud.oss.buket")
    private String buket;

    @Resource
    private OSSClient ossClient;

    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM");

    /**
     * 签名直传 - 获取签名数据
     *
     * @return map
     */
    @RequestMapping("oss/policy")
    public Map<String, String> ossPolicy() {
        // host的格式为 bucketname.endpoint
        String host = "https://" + buket + "." + endpoint;
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        // String callbackUrl = "http://88.88.88.88:8888";
        // 用户上传文件时指定的前缀。（每月一个目录）
        String dir = LocalDate.now().format(DF);
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为 5GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<>(8);
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));

            return respMap;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }

        return Collections.emptyMap();
    }
}
