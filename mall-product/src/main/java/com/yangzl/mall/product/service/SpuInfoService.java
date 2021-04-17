package com.yangzl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.product.entity.SpuInfoEntity;
import com.yangzl.mall.product.vo.SpuSaveVO;

import java.util.Map;

/**
 * spu信息
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    /**
     * 分页查询
     *
     * @param params param
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存 spu 信息
     *
     * @param spuInfo spuinfo
     */
    void saveSpuInfo(SpuSaveVO spuInfo);

    /**
     * 复杂条件查询
     *
     * @param params param
     * @return page
     */
    PageUtils queryByCondition(Map<String, Object> params);

    /**
     * 商品上架「spu sku 信息存入 es」
     *
     * @param spuId spuId
     */
    void up(Long spuId);
}

