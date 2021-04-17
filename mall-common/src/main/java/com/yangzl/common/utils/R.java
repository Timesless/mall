/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.yangzl.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R<E> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

    /**
     * 2021年4月17日 存放带类型的数据，避免 cast
     */
	private E data;

	public R() {
		put("code", 0);
		put("msg", "success");
	}

    public E getData() {
        return data;
    }

    /**
     * 设置泛型数据
     *
     * @param data data
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * 当前 R 是否是成功的调用
     *
     * @return bool
     */
    public boolean isSuccess() {
        return 0 == (Integer) this.get("code");
    }


    public static R error() {
		return error(Constant.SERVER_ERROR, "未知异常，请联系管理员");
	}

	public static R error(String msg) {
		return error(Constant.SERVER_ERROR, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	@Override
    public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

    /**
     * 利用 fastjson 获取复杂类型的数据
     *
     * 调用示例：
     *  构造一个匿名内部类
     *  TypeReference<List<SkuEsTo>> typeReference = new TypeReference<List<SkuEsTo>>(){};
     *  List<SkuEsTo> list = getData(typeReference);
     */
    public <T> T getTypedData(TypeReference<T> type) {
        String s = get("data").toString();

        return JSON.parseObject(s, type);
    }
}
