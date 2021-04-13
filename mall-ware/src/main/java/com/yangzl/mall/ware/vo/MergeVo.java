package com.yangzl.mall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc 合并采购单 VO
 */
@Data
public class MergeVo {

    private Long purchaseId;
    private List<Long> items;
}
