package com.yangzl.mall.third.config;

import com.aliyun.teaopenapi.models.Config;
import com.yangzl.mall.third.constant.StringConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信组件
 *
 * @author yangzl
 * @date 2021/5/15
 */
@Configuration
public class SMSConfig {

    @Bean
    public com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        Config config = new Config()
            // 您的AccessKey ID
            .setAccessKeyId(StringConstant.ALIYUN_ACCESS_KEY)
            // 您的AccessKey Secret
            .setAccessKeySecret(StringConstant.OSS_SCRET_KEY);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

}
