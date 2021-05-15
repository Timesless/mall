package com.yangzl.mall.product.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yangzl
 * @date 2021/5/15
 *
 * sku 销售属性
 */
@Getter
@Setter
@ToString
public class SkuItemSaleAttrVo {

    private Long attrId;
    private String attrName;
    private String attrValues;
}
