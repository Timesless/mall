package com.yangzl.mall.ware.controller;

import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.R;
import com.yangzl.mall.ware.entity.PurchaseEntity;
import com.yangzl.mall.ware.service.PurchaseService;
import com.yangzl.mall.ware.vo.MergeVo;
import com.yangzl.mall.ware.vo.PurchaseDoneVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 采购信息
 *
 * @author yangzl
 * @date 2020/11/19 21:28:43
 * @desc
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Resource
    private PurchaseService purchaseService;

    /**
     * 查询未被领取的采购单
     *
     * @param params param
     * @return R
     */
    @GetMapping("/unreceive/list")
    public R unreceiveList(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.queryUnreceivePage(params);

        return R.ok().put("page", page);
    }

    /**
     * 合并采购单
     *
     * @param mergeVo mergovo
     * @return R
     */
    @PostMapping("merge")
    public R mergePurchase(@RequestBody MergeVo mergeVo) {
        purchaseService.mergePurchase(mergeVo);

        return R.ok();
    }

    /**
     * 领取采购单
     *
     * @param ids ids
     * @return R
     */
    @PostMapping("receive")
    public R receivePurchase(@RequestParam List<Long> ids) {
        purchaseService.receivePurchase(ids);

        return R.ok();
    }

    /**
     * 完成采购单
     *
     * @param purchaseDoneVo done
     * @return R
     */
    @PostMapping("finish")
    public R finishPurchase(@RequestBody PurchaseDoneVo purchaseDoneVo) {
        purchaseService.done(purchaseDoneVo);

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PurchaseEntity purchase){
		purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PurchaseEntity purchase){
		purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
