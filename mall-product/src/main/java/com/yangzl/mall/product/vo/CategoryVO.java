package com.yangzl.mall.product.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yangzl.mall.product.entity.CategoryEntity;
import lombok.Data;

import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/7
 * @desc
 */
@Data
public class CategoryVO {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    private Long catId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父分类id
     */
    private Long parentCid;
    /**
     * 层级
     */
    private Integer catLevel;
    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer showStatus;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标地址
     */
    private String icon;
    /**
     * 计量单位
     */
    private String productUnit;
    /**
     * 商品数量
     */
    private Integer productCount;

    /**
     * 子节点，非数据库字段
     * @ JsonInclude 空值时可以选择是否序列化
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CategoryEntity> children;

}
