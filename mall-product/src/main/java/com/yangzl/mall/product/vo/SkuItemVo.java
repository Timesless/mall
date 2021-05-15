package com.yangzl.mall.product.vo;

import com.yangzl.mall.product.entity.SkuInfoEntity;
import com.yangzl.mall.product.entity.SpuInfoDescEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author yangzl
 * @date 2021/5/15
 */
@Getter
@Setter
@ToString
public class SkuItemVo {

    /** sku 基本信息 */
    private SkuInfoEntity skuInfoEntity;

    /** sku 图片 */
    private List<String> images;

    /** spu 所有的 sku 组合 */
    private List<SkuItemSaleAttrVo> saleAttr;

    /** spu 的介绍 */
    private SpuInfoDescEntity spuInfoDescEntity;

    /** spu 规格参数信息 */
    private List<SpuItemAttrGroupVo> groupAttrs;

}
