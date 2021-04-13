package com.yangzl.mall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc 完成采购单 VO
 */
@Data
public class PurchaseDoneVo {

    @NotNull
    private Long id;
    private List<PurchaseDoneItemVo> items;
}
