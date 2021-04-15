package com.yangzl.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.to.SkuReductionTo;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.coupon.dao.SkuFullReductionDao;
import com.yangzl.mall.coupon.entity.SkuFullReductionEntity;
import com.yangzl.mall.coupon.service.MemberPriceService;
import com.yangzl.mall.coupon.service.SkuFullReductionService;
import com.yangzl.mall.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商品满减信息
 *
 * @author yangzl
 * @date 2020/11/19 21:30:15
 * @desc
 */
@Service("skuFullReductionService")
@Transactional(rollbackFor = Exception.class)
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {

    @Resource
    private SkuLadderService skuLadderService;
    @Resource
    private MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveReduction(SkuReductionTo reductionTo) {
        /*
         * TODO
         * 1. 打折信息
         * 2.
         * 3.
         */
        SkuFullReductionEntity entity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(reductionTo, entity);
        this.save(entity);
    }
}
