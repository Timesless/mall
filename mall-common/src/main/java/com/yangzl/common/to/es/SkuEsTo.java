package com.yangzl.common.to.es;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/17
 * @desc es 实体类
 */
@Getter
@Setter
public class SkuEsTo {

    private Long spuId;
    private Long skuId;
    private String skuTitle;
    private BigDecimal skuPrice;
    private String skuImg;
    private Long saleCount;
    private Boolean hasStock;
    private Long hotScore;
    private Long brandId;
    private Long catalogId;
    private String brandName;
    private String brandImg;
    private String catalogName;
    private List<Attrs> attrs;

}
