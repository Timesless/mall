package com.yangzl.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.product.dao.SkuInfoDao;
import com.yangzl.mall.product.entity.SkuImagesEntity;
import com.yangzl.mall.product.entity.SkuInfoEntity;
import com.yangzl.mall.product.entity.SpuInfoDescEntity;
import com.yangzl.mall.product.service.*;
import com.yangzl.mall.product.vo.SkuItemSaleAttrVo;
import com.yangzl.mall.product.vo.SkuItemVo;
import com.yangzl.mall.product.vo.SpuItemAttrGroupVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * sku信息
 *
 * 商品详情过程复杂做异步编排
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 */
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {
    @Resource
    private SkuImagesService skuImagesService;
    @Resource
    private SpuInfoDescService spuInfoDescService;
    @Resource
    private AttrGroupService attrGroupService;
    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params), new QueryWrapper<>());

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

    @Override
    public SkuItemVo item(Long skuId) {
        // 1. sku 基本信息 pms_sku_info
        // 2. sku 图片信息 pms_sku_images
        // 3. 当前 sku 上级 spu 的所有 sku 组合
        // 4. 获取 spu 介绍
        // 5. 获取 spu 规格参数信息
        SkuItemVo item = new SkuItemVo();
        SkuInfoEntity infoEntity = this.getById(skuId);
        item.setSkuInfoEntity(infoEntity);

        List<SkuImagesEntity> images = skuImagesService.getImagesBySkuId(skuId);
        item.setImages(images.stream().map(SkuImagesEntity::getImgUrl).collect(Collectors.toList()));

        Long catalogId = infoEntity.getCatalogId(), spuId = infoEntity.getSpuId();
        List<SkuItemSaleAttrVo> attrs = skuSaleAttrValueService.getSaleAttrsBySpuId(spuId);
        item.setSaleAttr(attrs);

        SpuInfoDescEntity spuInfo = spuInfoDescService.getById(spuId);
        item.setSpuInfoDescEntity(spuInfo);

        List<SpuItemAttrGroupVo> attrGroups = attrGroupService.getAttrGroupWithAttrsBySpuId(spuId, catalogId);
        item.setGroupAttrs(attrGroups);

        return item;
    }
}
