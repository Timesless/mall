package com.yangzl.mall.third;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.io.File;

/**
 * oss
 * sms
 *
 * @author yangzl
 * @date 2021/4/6
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ThirdApplication.class)
public class ThirdTest {

    @Resource
    private OSSClient ossClient;

    @Resource
    com.aliyun.dysmsapi20170525.Client smsClient;


    /**
     * 测试文件上传
     */
    @Test
    public void testOssUpload() {
        ossClient.getObject(new GetObjectRequest("yangzl", "2E5B2803DB9C710229CA4FAF15766B43.jpg"),
            new File("C:/Users/yangzl/Pictures/Wall Paper/2E5B2803DB9C710229CA4FAF15766B43.jpg"));
    }

    /**
     * 发送短信
     */
    @Test
    public void testSms() throws Exception {

        SendSmsRequest smsRequest = new SendSmsRequest();
        smsRequest
            .setPhoneNumbers("15708182023")
            .setSignName("yangzl")
            .setTemplateCode("SMS_122310183");

        smsClient.sendSms(smsRequest);
    }
}
