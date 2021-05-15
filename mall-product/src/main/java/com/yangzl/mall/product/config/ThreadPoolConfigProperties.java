package com.yangzl.mall.product.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 当作为 component 时，在获取配置的类可以不用 EnableConfigurationProperties
 *
 * @author yangzl
 * @date 2021/5/15
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.task.execution.pool")
public class ThreadPoolConfigProperties {

    private int coreSize = 8;
    private int maxSize = 32;
    private int queueCapacity = 256;
}
