package com.yangzl.mall.seckill.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;

import com.yangzl.mall.seckill.dao.CouponSpuRelationDao;
import com.yangzl.mall.seckill.entity.CouponSpuRelationEntity;
import com.yangzl.mall.seckill.service.CouponSpuRelationService;

/**
 * 优惠券与产品关联
 *
 * @author yangzl
 * @date 2020/11/19 21:30:15
 * @desc
 */

@Service("couponSpuRelationService")
public class CouponSpuRelationServiceImpl extends ServiceImpl<CouponSpuRelationDao, CouponSpuRelationEntity> implements CouponSpuRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponSpuRelationEntity> page = this.page(
                new Query<CouponSpuRelationEntity>().getPage(params),
                new QueryWrapper<CouponSpuRelationEntity>()
        );

        return new PageUtils(page);
    }

}
