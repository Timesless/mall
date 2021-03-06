package com.yangzl.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.coupon.dao.CouponHistoryDao;
import com.yangzl.mall.coupon.entity.CouponHistoryEntity;
import com.yangzl.mall.coupon.service.CouponHistoryService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 优惠券领取历史记录
 *
 * @author yangzl
 * @date 2020/11/19 21:30:15
 * @desc
 */

@Service("couponHistoryService")
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryDao, CouponHistoryEntity> implements CouponHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponHistoryEntity> page = this.page(
                new Query<CouponHistoryEntity>().getPage(params),
                new QueryWrapper<CouponHistoryEntity>()
        );

        return new PageUtils(page);
    }

}
