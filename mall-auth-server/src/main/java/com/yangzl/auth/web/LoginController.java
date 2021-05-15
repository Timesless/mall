package com.yangzl.auth.web;

import com.yangzl.auth.service.MessageService;
import com.yangzl.common.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录
 * Spring MVC view controller @see com.yangzl.auth.config.WebControllerConfig
 *
 * @author yangzl
 * @date 2021/5/15
 */
@RestController
public class LoginController {

    @Resource
    MessageService messageService;

    /*@GetMapping("/login.html")
    public String login() {

        return "login";
    }
    @GetMapping("/reg.html")
    public String reg() {

        return "reg";
    }*/

    /**
     * 防止重复提交「将 code 存入 redis 保存 10 min」
     *
     * @param phone phone
     * @return r
     */
    @GetMapping("/sms/code")
    public R sendLoginCode(@RequestParam("phone") String phone) {

        return messageService.sendMsgCode(phone);
    }
}
