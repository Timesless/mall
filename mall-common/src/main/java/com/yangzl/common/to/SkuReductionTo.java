package com.yangzl.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc
 */
@Data
public class SkuReductionTo {

    private Long skuId;
    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;

}
