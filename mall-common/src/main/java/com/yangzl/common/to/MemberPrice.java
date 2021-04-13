package com.yangzl.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc
 */
@Data
public class MemberPrice {

    private Long id;
    private String name;
    private BigDecimal price;
}
