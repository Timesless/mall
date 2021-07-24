package com.yangzl.mall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangzl.mall.product.entity.BrandEntity;
import com.yangzl.mall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author yangzl
 * @date 2020/11/19 21:47
 * @desc
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProductApplication7076.class)
public class ProductTest {

    Logger logger = LoggerFactory.getLogger(ProductTest.class);

    @Resource
    private BrandService brandService;

    @Test
    public void testAdd() {
        BrandEntity brand = new BrandEntity();
        brand.setName("Apple");
        brand.setDescript("Apple.INC");
        brandService.save(brand);
        logger.info("保存成功");
    }

    @Test
    public void testQuery() {
        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
        logger.info(list.toString());
    }

    @Test
    public void testFileUpload() throws IOException {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "oss-cn-chengdu.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        // String accessKeyId = "LTAI5t5i2CF3vnosEsZvNq83";
        // String accessKeySecret = "Jz90M0TyxHpx4rgnuoKiEDZuoG4dmy";

        // 创建OSSClient实例。
        /*OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = new FileInputStream("D:\\localpath\\examplefile.txt");
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("yangzl", "exampleobject.txt", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();*/
    }
}
