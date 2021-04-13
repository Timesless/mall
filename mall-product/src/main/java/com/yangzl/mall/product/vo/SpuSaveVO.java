package com.yangzl.mall.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc
 */
@Data
public class SpuSaveVO {

    private String spuName;
    private String spuDescription;
    private Long catelogId;
    private Long brandId;
    private BigDecimal weight;
    private Integer publishStatus;
    private List<String> descript;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttr> baseAttrs;
    private List<Skus> skus;

}
