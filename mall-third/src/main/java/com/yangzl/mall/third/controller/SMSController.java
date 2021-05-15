package com.yangzl.mall.third.controller;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.yangzl.common.enums.ExceptionEnum;
import com.yangzl.common.utils.R;
import com.yangzl.mall.third.constant.StringConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 阿里云短信平台
 *
 * @author yangzl
 * @date 2021/5/15
 */
@Slf4j
@RestController
public class SMSController {

    @Resource
    com.aliyun.dysmsapi20170525.Client client;

    @PostMapping("/sms/send")
    public R sendSms(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        try {
            client.sendSms(new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("yangzl")
                .setTemplateCode(StringConstant.SMS_TEMPLATE_CODE)
                .setTemplateParam(code));
        } catch (Exception e) {
            log.error("短信发送失败，异常信息为：{}", e.getMessage());
            return R.error(ExceptionEnum.SMS_SEND_EXCEPTION.getCode(), ExceptionEnum.SMS_SEND_EXCEPTION.getMsg());
        }
        return R.ok();
    }

}
