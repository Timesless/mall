# mall
高并发，分布式架构 尚硅谷谷粒商城实现



### 微服务

1. 后台管理系统
2. 网关服务
3. 商品系统
4. 全文检索系统
5. 订单系统
6. 购物车系统
7. 仓库系统
8. 秒杀系统
9. 物流系统
10. 会员系统
11. 优惠系统
12. 支付系统

### 技术栈

> 反向代理、动静分离 - Nginx
>
> 注册中心、配置中心 - nacos
>
> 认证中心（社交登录，weibo） - OAuth2.0
>
> 权限控制 - Spring Security
>
> 统一网关 - SpringCloud Gateway
>
> 客户端负载均衡 - Ribbon
>
> 服务熔断降级 - Sentinel
>
> 缓存系统 - redis
>
> 分布式事务 - Seata
>
> 远程调用 - openFeign
>
> 对象存储 - 阿里云对象存储 OSS
>
> 全文检索 - Elasticsearch
>
> 消息队列 - RabbitMQ
>
> 链路追踪 - Zipkin + Sleuth
>
> 线上监控系统 - Prometheus
>
> 日志系统 - ElasticSearch + Logstash + Kibana
>
> 压力测试 - Jmeter
>
> 性能优化
>
> 人人快速开发平台
>
> 支付宝API
>
> 容器化技术 - Docker
>
> 声明式可配式容器负载和服务管理平台 - Kubernets



## 初级篇


### 中间件安装

+ MySQL
```shell script
docker un -p 3306:3306 --name mysql\
-v /mall/mysql/log:/var/log/mysql\
-v /mall/mysql/data:/var/lib/mysql\
-v /mall/mysql/conf:/etc/mysql\
-e MYSQL_ROOT_PASSWORD=123456\
-d mysql

# -p 端口映射 宿主机端口/容器端口
# -v 目录挂载 宿主机目录/容器目录
# -e MYSQL_ROOT_PASSWORD=123456，初始化root密码

docker exec -it mysql /bin/bash
docker update mysql --restart=always
```

+ Nginx
```shell script

```

+ Redis
```shell script
docker run -p 6379:6379 --name redis\
-v /mall/redis/data:/data\
-v /mall/redis/conf/redis.conf:/etc/redis/redis.conf\
-d redis redis-server /etc/redis/redis.conf

# 以 /etc/redis/redis.conf 配置启动

docker exec -it redis redis-cli
docker update mysql --restart=always
```

+ ElasticSearch



+ Kibana


### renren-fast
> renren 快速开发平台，生成后台管理系统 mall-admin
> 创建db并执行db下sql文件

### renren-generator
> renren 逆向代码生成，包括5个系统：
> 1. coupon 优惠系统，mall_sms
> 2. member 会员系统，mall_ums
> 3. order 订单系统，mall_oms
> 4. product 商品系统，mall_pms
> 5. ware 仓库系统，mall_wms

将逆向生成的Java代码，copy至各项目

### startup

> mall-common
1. 导入Mybatis-Plus
    `compile 'com.baomidou:mybatis-plus-boot-starter:3.4.0'`
2. 导入MySQL驱动
    `runtime 'mysql:mysql-connector-java:8.0.22'`
3. 配置数据源
    hikari 速度最快的数据源

> nacos - 注册中心
1. 引入 nacos
    `com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery`
2. 配置 nacos server地址
    `spring.cloud.nacos.server-addr: 127.0.0.1:8848`
3. 开启注解
    `@EnableDiscoveryClient`
    
> openfeign - 远程调用
1. 引入 openfeign
    `org.springframework.cloud:spring-cloud-starter-openfeign`
2. 开启注解
    `@EnableFeignClients(basePackages="com.yangzl.mall.product.feign")`
3. 声明式接口
    编写声明式接口，标注`@FeignClient("application-name")`，声明式调用（mvc 注解路径）
    
> nacos - 配置中心
1. 引入 依赖
    `com.alibaba.clous:spring-cloud-starter-alibaba-nacos-config`
2. 创建bootstrap.properties（bootstrap优先于application加载）
    添加项目名称与nacos server地址 `spring.application.name=, spring.cloud.nacos.config.server-addr=`
3. 项目启动默认读取(`${application.name}.properties`)
    所以在nacos配置创建文件 `${application.name}.properties` 并添加配置
4. 在要使用动态配置的组件添加注解 `@RefreshScope`

#### nacos 使用详解：
> 配置中心优先于本地配置

1. 命名空间
2. 配置集
3. 配置集ID












## 高级篇















## 分布式篇
