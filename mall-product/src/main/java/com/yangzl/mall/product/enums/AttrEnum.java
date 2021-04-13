package com.yangzl.mall.product.enums;

/**
 * @author yangzl
 * @date 2021/4/8
 * @desc
 */
public enum AttrEnum {

    /**
     * 属性
     */
    ATTR_BASE(1, "基本属性"),
    ATTR_SALE(0, "销售属性"),
    ATTR_ALL(2, "销售属性和基本属性");

    private int code;
    private String desc;

    AttrEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
