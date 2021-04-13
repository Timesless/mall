package com.yangzl.mall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author yangzl
 * @date 2021/4/6
 * @desc 根据同源策略对 HTTP 请求进行限制
 *      1. 协议
 *      2. 域名
 *      3. 端口
 *          1 2 3任意一个不相同，则被同源策略拒绝
 *
 *  跨域流程「非简单请求（简单请求：GET, HEAD, POST（表单提交，多部份表单提交，text/plain））」
 *      1. 首先发送预检请求 OPTIONS
 *      2. 实际请求
 *
 *  解决跨域：
 *          1. nginx 将前端，后台项目部署到同一个域
 *          2. 预检请求 OPTIONS 响应为允许跨域
 *              Access-Control-Allow-Origin：支持哪些来源的请求跨域
 *              Access-Control-Allow-Methods：支持哪些方法跨域
 *              Access-Control-Allow-Credentials：带 cookie 跨域
 *              Access-Control-Expose-Headers：跨域请求暴露的字段
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);

        return new CorsWebFilter(source);
    }
}
