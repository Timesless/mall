package com.yangzl.mall.coupon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangzl.mall.coupon.entity.HomeSubjectEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author yangzl
 * @date 2020/11/19 21:30:15
 * @desc
 */
@Mapper
public interface HomeSubjectDao extends BaseMapper<HomeSubjectEntity> {

}
