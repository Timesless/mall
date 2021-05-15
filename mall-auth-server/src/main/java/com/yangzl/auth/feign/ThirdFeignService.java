package com.yangzl.auth.feign;

import com.yangzl.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 第三方服务 feign 声明
 * 
 * @author yangzl
 * @date 2021/5/15
 */
@FeignClient("mall-third")
public interface ThirdFeignService {

    /**
     * feign 调用第三方服务发送短信
     *
     * @param phone phone
     * @param code code
     * @return R
     */
    @PostMapping("/sms/send")
    R sendSms(@RequestParam("phone") String phone, @RequestParam("code") String code);
}
