package com.yangzl.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.product.dao.SkuInfoDao;
import com.yangzl.mall.product.entity.SkuInfoEntity;
import com.yangzl.mall.product.service.SkuInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */

@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryByCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> wrapper = new QueryWrapper<>();
        String key, catelogId, brandId;
        if (StringUtils.hasLength(key = (String) params.get("key"))) {
            wrapper.and(w -> w.eq("sku_id", key).or().like("sku_name", key));
        }
        if (StringUtils.hasLength(brandId = (String) params.get("brandId"))) {
            wrapper.eq("brand_id", brandId);
        }
        if (StringUtils.hasLength(catelogId = (String) params.get("catelogId"))) {
            wrapper.eq("catelog_id", catelogId);
        }
        Double min, max;
        if ((min = (Double) params.get("min")) > 0) {
            wrapper.ge("price", min);
        }
        if ((max = (Double) params.get("max")) > 0) {
            wrapper.le("price", max);
        }
        IPage<SkuInfoEntity> page = this.page(new Query<SkuInfoEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

    @Override
    public List<SkuInfoEntity> getSkusBySpuId(Long spuId) {
        return this.list(new QueryWrapper<SkuInfoEntity>().eq("spu_id", spuId));
    }
}
