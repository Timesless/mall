package com.yangzl.mall.seckill.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;

import com.yangzl.mall.seckill.dao.SeckillPromotionDao;
import com.yangzl.mall.seckill.entity.SeckillPromotionEntity;
import com.yangzl.mall.seckill.service.SeckillPromotionService;

/**
 * 秒杀活动
 *
 * @author yangzl
 * @date 2020/11/19 21:30:15
 * @desc
 */

@Service("seckillPromotionService")
public class SeckillPromotionServiceImpl extends ServiceImpl<SeckillPromotionDao, SeckillPromotionEntity> implements SeckillPromotionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillPromotionEntity> page = this.page(
                new Query<SeckillPromotionEntity>().getPage(params),
                new QueryWrapper<SeckillPromotionEntity>()
        );

        return new PageUtils(page);
    }

}
