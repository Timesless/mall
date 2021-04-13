package com.yangzl.common.enums;

/**
 * @author yangzl
 * @date 2021/4/13
 * @desc
 */
public enum PurchaseStatusEnum {

    /** 订单状态 */
    CREATED(0, "新建"),
    ASSIGNED(1, "已分配"),
    RECEIVE(2, "已领取"),
    FINISH(3, "已完成"),
    ERROR(4, "异常");

    private final int code;
    private final String desc;

    PurchaseStatusEnum(int code, String desc) {
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
