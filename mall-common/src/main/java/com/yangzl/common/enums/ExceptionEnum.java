package com.yangzl.common.enums;

/**
 * @author yangzl
 * @date 2021/4/7
 * @desc
 *
 *  *  异常 code 码定义为 5 位数字，前两位表示业务场景，后三位表示错误码
 *  *      10xxx：通用异常
 *  *          000：未知异常
 *  *          001：参数格式校验异常
 *  *          004：运行时异常
 *  *          008：DAO 异常
 *  *      11xxx：商品业务
 *  *      12xxx：订单业务
 *  *      13xxx：购物车业务
 *  *      14xxx：物流业务
 */
public enum ExceptionEnum {

    /**
     * 异常定义
     */
    UNKOWN_EXCEPTION(10000, "未知异常"),
    VALID_EXCEPTION(10001, "参数校验失败"),
    RUNTIME_EXCEPTION(10004, "运行时异常"),
    DAO_EXCEPTION(10008, "DAO异常"),

    PRODUCT_SHELVES_EXCEPTION(11001, "商品上架异常");

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    int code;
    String msg;

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
