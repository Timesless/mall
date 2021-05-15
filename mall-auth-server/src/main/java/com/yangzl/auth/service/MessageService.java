package com.yangzl.auth.service;

import com.yangzl.common.utils.R;

/**
 * 短信
 *
 * @author yangzl
 * @date 2021/5/15
 */
public interface MessageService {

    /**
     * 发送短信验证码
     *
     * @param phone phone
     * @return r
     */
    R sendMsgCode(String phone);
}
