package com.yangzl.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yangzl
 * @date 2021/4/17
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category2Vo {

    /** 父分类 id，1 级分类 id */
    private String catalogId;
    /** 三级子分类 */
    private List<Category3Vo> catalog3List;
    private String id;
    private String name;

}
