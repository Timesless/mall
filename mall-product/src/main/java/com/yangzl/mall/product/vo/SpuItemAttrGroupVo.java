package com.yangzl.mall.product.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author yangzl
 * @date 2021/5/15
 */
@Getter
@Setter
@ToString
public class SpuItemAttrGroupVo {

    private String groupName;
    private List<Attr> attrs;
}
