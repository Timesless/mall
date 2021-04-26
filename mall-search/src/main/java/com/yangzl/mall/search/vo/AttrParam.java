package com.yangzl.mall.search.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/19
 * @desc
 */
@Getter
@Setter
public class AttrParam {

    private Long attrId;
    private String attrName;
    private List<String> attrValue;
}
