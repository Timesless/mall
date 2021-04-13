package com.yangzl.mall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc
 */
@Data
public class Bounds {

    /** 积分 */
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
