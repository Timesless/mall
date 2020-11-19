package com.yangzl.mall.member.dao;

import com.yangzl.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 *
 * @author yangzl
 * @date 2020/11/19 21:29:28
 * @desc
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {

}
