package com.yangzl.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.product.dao.SkuImagesDao;
import com.yangzl.mall.product.entity.SkuImagesEntity;
import com.yangzl.mall.product.service.SkuImagesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * sku图片
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */

@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuImagesEntity> page = this.page(
                new Query<SkuImagesEntity>().getPage(params),
                new QueryWrapper<SkuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuImagesEntity> getImagesBySkuId(Long skuId) {

        return baseMapper.selectList(new QueryWrapper<SkuImagesEntity>().select("id", "img_url").eq("sku_id", skuId));
    }
}
