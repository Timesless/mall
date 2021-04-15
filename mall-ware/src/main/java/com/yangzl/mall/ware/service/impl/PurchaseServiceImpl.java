package com.yangzl.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.enums.PurchaseStatusEnum;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.ware.dao.PurchaseDao;
import com.yangzl.mall.ware.entity.PurchaseDetailEntity;
import com.yangzl.mall.ware.entity.PurchaseEntity;
import com.yangzl.mall.ware.service.PurchaseDetailService;
import com.yangzl.mall.ware.service.PurchaseService;
import com.yangzl.mall.ware.service.WareSkuService;
import com.yangzl.mall.ware.vo.MergeVo;
import com.yangzl.mall.ware.vo.PurchaseDoneVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 采购信息
 *
 * @author yangzl
 * @date 2020/11/19 21:28:43
 * @desc
 */
@Service("purchaseService")
@Transactional(rollbackFor = Exception.class)
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Resource
    private PurchaseDetailService purchaseDetailService;
    @Resource
    private WareSkuService wareSkuService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryUnreceivePage(Map<String, Object> params) {
        QueryWrapper<PurchaseEntity> wrapper = new QueryWrapper<PurchaseEntity>().eq("status", 0).or().eq("status", 1);
        IPage<PurchaseEntity> page = this.page(new Query<PurchaseEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

    @Override
    public void mergePurchase(MergeVo mergeVo) {
        Long purchaseId = 0L;
        if (null ==  mergeVo.getPurchaseId()) {
            PurchaseEntity purchase = new PurchaseEntity();
            Date cur = new Date();
            purchase.setCreateTime(cur);
            purchase.setUpdateTime(cur);
            purchase.setStatus(PurchaseStatusEnum.CREATED.getCode());
            this.save(purchase);
            purchaseId = purchase.getId();
        }
        final Long fpid = purchaseId;
        List<PurchaseDetailEntity> details = mergeVo.getItems().stream().map(id -> {
            PurchaseDetailEntity detail = new PurchaseDetailEntity();
            detail.setId(id);
            detail.setPurchaseId(fpid);
            detail.setStatus(0);

            return detail;
        }).collect(Collectors.toList());

        purchaseDetailService.saveBatch(details);
    }

    @Override
    public void receivePurchase(List<Long> ids) {
        List<PurchaseEntity> purchaseEntities = this.listByIds(ids).stream()
            .filter(item -> item.getStatus() == 0 || item.getStatus() == 1).collect(Collectors.toList());

        this.updateBatchById(purchaseEntities);
    }

    @Override
    public void done(PurchaseDoneVo purchaseDoneVo) {
        /*
         * 1. 修改采购单状态
         * 2. 改变采购项状态
         * 3. 成功采购的商品 入库
         */
        Long id = purchaseDoneVo.getId();
        // 所有采购项成功，采购单才算是成功
        boolean purchaseFlag = purchaseDoneVo.getItems().stream()
            .noneMatch(item -> PurchaseStatusEnum.ERROR.getCode() == item.getStatus());
    }
}
