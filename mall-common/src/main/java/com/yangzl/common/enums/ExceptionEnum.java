package com.yangzl.common.enums;

/**
 * @author yangzl
 * @date 2021/4/7
 * @desc
 */
public enum ExceptionEnum {

    /**
     * 异常定义
     */
    UNKOWN_EXCEPTION(10000, "未知异常"),
    VALID_EXCEPTION(10001, "参数校验失败"),
    RUNTIME_EXCEPTION(10004, "运行时异常"),
    DAO_EXCEPTION(10008, "DAO异常");

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
