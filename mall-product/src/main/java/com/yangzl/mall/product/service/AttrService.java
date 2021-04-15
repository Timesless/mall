package com.yangzl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.product.entity.AttrEntity;
import com.yangzl.mall.product.vo.AttrGroupRelationVO;
import com.yangzl.mall.product.vo.AttrRespVO;
import com.yangzl.mall.product.vo.AttrVO;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
public interface AttrService extends IService<AttrEntity> {

    /**
     * 分页查询
     *
     * @param params params
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 1. 保存基本信息
     * 2. 保存关联关系
     *
     * @param attr attrvo
     */
    void saveAttr(AttrVO attr);

    /**
     * 按条件分页查询 基本属性 sku
     *
     * @param params params
     * @param catelogId categoryId
     * @return page
     */
    PageUtils queryAttrBaseInfoPage(Map<String, Object> params, Long catelogId);

    /**
     * 按条件分页查询 销售属性 sku
     *
     * @param params params
     * @param catelogId catelogId
     * @return page
     */
    PageUtils queryAttrSaleInfoPage(Map<String, Object> params, Long catelogId);

    /**
     * 返回 AttrRespVO
     * @param attrId attrId
     * @return AttrRespVO
     */
    AttrRespVO getAttrInfoById(Long attrId);

    /**
     * 1. 更新基本信息
     * 2. 更新关联信息
     *
     * @param attr attrvo
     */
    void updateCascade(AttrVO attr);

    /**
     * 根据分组 id 查找所有关联基本属性
     *
     * @param attrgroupId attrgroupId
     * @return list
     */
    List<AttrEntity> getRelationAttr(Long attrgroupId);

    /**
     * 删除关联
     *
     * @param vos vos
     */
    void deleteRelation(List<AttrGroupRelationVO> vos);

    /**
     * 获取当前分组没有关联的属性
     *
     * @param attrgroupId groupId
     * @param params param
     * @return page
     */
    PageUtils getNoRelationAttr(Long attrgroupId, Map<String, Object> params);
}

