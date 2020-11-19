package com.yangzl.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author yangzl
 * @date 2020/11/19 21:29:28
 * @desc
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

