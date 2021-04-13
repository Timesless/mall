package com.yangzl.mall.ware.vo;

import lombok.Data;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc
 */
@Data
public class PurchaseDoneItemVo {

    private Long itemId;
    private Integer status;
    private String reason;
}
