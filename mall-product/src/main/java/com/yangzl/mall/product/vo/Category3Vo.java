package com.yangzl.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangzl
 * @date 2021/4/17
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category3Vo {

    /**
     * 父分类，2级分类 id
     */
    private String catalog2Id;
    private String id;
    private String name;
}
