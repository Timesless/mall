package com.yangzl.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.to.SkuReductionTo;
import com.yangzl.common.to.SpuBoundTo;
import com.yangzl.common.to.es.Attrs;
import com.yangzl.common.to.es.SkuEsTo;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.common.utils.R;
import com.yangzl.mall.product.dao.SpuInfoDao;
import com.yangzl.mall.product.entity.ProductAttrValueEntity;
import com.yangzl.mall.product.entity.SkuImagesEntity;
import com.yangzl.mall.product.entity.SkuInfoEntity;
import com.yangzl.mall.product.entity.SpuInfoEntity;
import com.yangzl.mall.product.feign.CouponFeignService;
import com.yangzl.mall.product.feign.SearchFeignService;
import com.yangzl.mall.product.feign.WareFeignService;
import com.yangzl.mall.product.service.*;
import com.yangzl.mall.product.vo.Images;
import com.yangzl.mall.product.vo.SpuSaveVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * spu信息
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
@Slf4j
@Service("spuInfoService")
@Transactional(rollbackFor = Exception.class)
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Resource
    private SpuInfoDescService spuInfoDescService;
    @Resource
    private SpuImagesService spuImagesService;
    @Resource
    private SkuInfoService skuInfoService;
    @Resource
    private SkuImagesService skuImagesService;
    @Resource
    private BrandService brandService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private SearchFeignService searchFeignService;
    @Resource
    private ProductAttrValueService productAttrValueService;
    @Resource
    private WareFeignService wareFeignService;

    /** feign service */
    @Resource
    private CouponFeignService couponFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * TODO
     *
     * @param spuInfo spuinfo
     */
    @Override
    public void saveSpuInfo(SpuSaveVO spuInfo) {
        /*
         * 1. 保存 spu 基本信息：pms_spu_info
         * 2. 保存 spu 描述图片：pms_spu_info_desc
         * 3. 保存 spu 图片集：pms_spu_images
         * 4. 保存 spu 规格参数：pms_product_attr_value
         * 5. 保存 spu 对应的 sku 信息
         *      5.1 sku 基本信息：pms_sku_info
         *      5.2 sku 图片信息：pms_sku_images
         *      5.3 sku 销售属性信息：pms_sku_sale_attr_value
         *      5.4 sku 优惠满减信息：mall-sms.sms_sku_ladder
         * 6. 保存 spu 积分信息：mall-sms.sms_spu_bounds
         */
        SpuInfoEntity baseEntity = new SpuInfoEntity();
        Date curDate = new Date();
        baseEntity.setCreateTime(curDate);
        baseEntity.setUpdateTime(curDate);
        BeanUtils.copyProperties(spuInfo, baseEntity);
        this.save(baseEntity);

        List<SkuInfoEntity> skus = spuInfo.getSkus().stream().map(spu -> {
            SkuInfoEntity sku = new SkuInfoEntity();
            BeanUtils.copyProperties(spu, sku);
            sku.setBrandId(baseEntity.getBrandId());
            sku.setCatalogId(baseEntity.getCatalogId());
            sku.setSaleCount(0L);
            sku.setSpuId(baseEntity.getId());
            String defaultImage = spu.getImages().stream()
                .filter(img -> 1 == img.getDefaultImage()).map(Images::getImageUrl).limit(1).collect(Collectors.joining());
            sku.setSkuDefaultImg(defaultImage);

            return sku;
        }).collect(Collectors.toList());
        skuInfoService.saveBatch(skus);

        int sz;
        List<SkuImagesEntity> images = new ArrayList<>(sz = skus.size());
        for (int i = 0; i < sz; i++) {
            SkuImagesEntity image = new SkuImagesEntity();
            SkuInfoEntity sku = skus.get(i);
            image.setSkuId(sku.getSkuId());
            // TODO
        }
        skuImagesService.saveBatch(images);

        // 远程服务调用
        SpuBoundTo boundTo = new SpuBoundTo();
        BeanUtils.copyProperties(spuInfo.getBounds(), boundTo);
        boundTo.setSpuId(baseEntity.getId());
        couponFeignService.saveSpuBounds(boundTo);

        skus.forEach(sku -> {
            SkuReductionTo skuTo = new SkuReductionTo();
            BeanUtils.copyProperties(sku, skuTo);
            couponFeignService.saveSkuReduction(skuTo);
        });
    }

    @Override
    public PageUtils queryByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        String key, status, brandId, catelogId;
        if (StringUtils.hasLength(key = (String) params.get("key"))) {
            wrapper.and(w -> w.eq("id", key).or().like("spu_name", key));
        }
        if (StringUtils.hasLength(status = (String) params.get("status"))) {
            wrapper.eq("publish_status", status);
        }
        if (StringUtils.hasLength(brandId = (String) params.get("brandId"))) {
            wrapper.eq("brand_id", brandId);
        }
        if (StringUtils.hasLength(catelogId = (String) params.get("catelogId"))) {
            wrapper.eq("catelog_id", catelogId);
        }
        IPage<SpuInfoEntity> page = this.page(new Query<SpuInfoEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {
        List<SkuInfoEntity> skus = skuInfoService.getSkusBySpuId(spuId);
        // 查询可被检索的属性
        List<ProductAttrValueEntity> searchAttrs = productAttrValueService.selectSearchAttrIds(spuId);
        List<Attrs> search = searchAttrs.stream().map(attr -> {
            Attrs attrs = new Attrs();
            BeanUtils.copyProperties(attr, attrs);

            return attrs;
        }).collect(Collectors.toList());

        // 统一库存查询
        try {
            List<Long> skuIds = skus.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());
            List<Long> hasStocks = wareFeignService.skusHasStock(skuIds).getData();
        } catch (Exception e) {
            log.error("......远程库存查询异常，失败原因：{}", e.getMessage());
        }

        List<SkuEsTo> tos = skus.stream().map(sku -> {
            SkuEsTo to = new SkuEsTo();
            BeanUtils.copyProperties(sku, to);
            to.setSkuImg(sku.getSkuDefaultImg());
            to.setSkuPrice(sku.getPrice());
            // hasStock, hotScore, brandName, brandImg, catalogName, attrs
            // 默认有库存，远程调用 ware 查询
            to.setHasStock(Boolean.TRUE);
            /*
             * 1. 3. 查询 sku 所有可以被检索的属性信息
             * 2. 远程调用 ware hasStock
             * 3. 查询品牌分类和名称
             */
            to.setAttrs(search);

            return to;
        }).collect(Collectors.toList());

        // 调用检索服务，上架 tos
        R r = searchFeignService.up(tos);
        if (r.isSuccess()) {
            // 状态修改为已上架
            SpuInfoEntity updateEntity = new SpuInfoEntity();
            updateEntity.setId(spuId);
            updateEntity.setPublishStatus(2);
            updateEntity.setUpdateTime(new Date());
            baseMapper.updateById(updateEntity);
        } else {
            // 重试机制「幂等性」
        }

    }
}
