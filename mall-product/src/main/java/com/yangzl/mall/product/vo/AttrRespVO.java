package com.yangzl.mall.product.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yangzl
 * @date 2021/4/7
 * @desc 响应 VO
 *      1. catelogName："手机/数码/手机"
 *      2. groupName："主体"
 */
@Getter
@Setter
@ToString
public class AttrRespVO extends AttrVO{

    private String catelogName;
    private Long[] catelogPath;
    private String groupName;
}
