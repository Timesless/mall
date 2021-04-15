package com.yangzl.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.ware.dao.WareSkuDao;
import com.yangzl.mall.ware.entity.WareSkuEntity;
import com.yangzl.mall.ware.service.WareSkuService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 商品库存
 *
 * @author yangzl
 * @date 2020/11/19 21:28:43
 * @desc
 */
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        String skuId, wareId;
        if (StringUtils.hasLength(skuId = (String) params.get("skuId"))) {
            wrapper.eq("sku_id", skuId);
        }
        if (StringUtils.hasLength(wareId = (String) params.get("wareId"))) {
            wrapper.eq("ware_id", wareId);
        }
        IPage<WareSkuEntity> page = this.page(new Query<WareSkuEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

}
