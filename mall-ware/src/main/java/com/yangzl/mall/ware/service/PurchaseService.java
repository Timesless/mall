package com.yangzl.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.mall.ware.entity.PurchaseEntity;
import com.yangzl.mall.ware.vo.MergeVo;
import com.yangzl.mall.ware.vo.PurchaseDoneVo;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author yangzl
 * @date 2020/11/19 21:28:43
 * @desc
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    /**
     * 分页查询所有
     *
     * @param params param
     * @return page
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询未被领取的采购单
     *
     * @param params param
     * @return page
     */
    PageUtils queryUnreceivePage(Map<String, Object> params);

    /**
     * 采购单合并
     *
     * @param mergeVo mergevo
     */
    void mergePurchase(MergeVo mergeVo);

    /**
     * 领取采购单
     *
     * @param ids ids
     */
    void receivePurchase(List<Long> ids);

    /**
     * 完成采购案
     *
     * @param purchaseDoneVo donevo
     */
    void done(PurchaseDoneVo purchaseDoneVo);
}

