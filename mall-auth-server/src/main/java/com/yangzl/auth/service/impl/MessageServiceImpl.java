package com.yangzl.auth.service.impl;

import com.yangzl.common.enums.ExceptionEnum;
import com.yangzl.common.utils.R;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.UUID;

/**
 * @author yangzl
 * @date 2021/5/15
 */
@Component
public class MessageServiceImpl implements MessageService {

    private long interval = 60L * 10000;

    @Resource
    ThirdFeignService thirdFeignService;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 1. 接口防刷 TODO
     * 2. 限制频率「60s / 次」
     *
     * @param phone phone
     * @return R
     */
    @Override
    public R sendMsgCode(String phone) {
        String code = UUID.randomUUID().toString().substring(0, 4);
        // 追加 timestamp 60s 内防重复提交
        String codeKey = phone + "_" + System.currentTimeMillis();
        String redisVal = stringRedisTemplate.opsForValue().get(codeKey);
        if (StringUtils.hasLength(redisVal)) {
            if (System.currentTimeMillis() - Long.parseLong(redisVal.split("_")[1]) < interval) {

                return R.error(ExceptionEnum.SMS_SEND_FREQUENT.getCode(),
                    ExceptionEnum.SMS_SEND_FREQUENT.getMsg());
            }
        } else {
            stringRedisTemplate.opsForValue().set(codeKey, code, Duration.ofMinutes(10));
        }

        // 第三方不能保证短信一定发送成功
        return thirdFeignService.sendSms(phone, code);
    }
}
