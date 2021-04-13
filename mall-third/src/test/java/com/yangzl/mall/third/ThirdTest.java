package com.yangzl.mall.third;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author yangzl
 * @date 2021/4/6
 * @desc
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ThirdApplication.class)
public class ThirdTest {

    @Resource
    private OSSClient ossClient;


    @Test
    public void testOssUpload() {
        ossClient.getObject(new GetObjectRequest("yangzl", "2E5B2803DB9C710229CA4FAF15766B43.jpg"),
            new File("C:/Users/yangzl/Pictures/Wall Paper/2E5B2803DB9C710229CA4FAF15766B43.jpg"));
    }
}
