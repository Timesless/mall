package com.yangzl.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc 微服务之间数据传输对象 SpuBoundTo「积分」
 */
@Data
public class SpuBoundTo {

    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
