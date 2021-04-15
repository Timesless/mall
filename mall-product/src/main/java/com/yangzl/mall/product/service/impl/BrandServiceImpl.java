package com.yangzl.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.product.dao.BrandDao;
import com.yangzl.mall.product.entity.BrandEntity;
import com.yangzl.mall.product.entity.CategoryBrandRelationEntity;
import com.yangzl.mall.product.service.BrandService;
import com.yangzl.mall.product.service.CategoryBrandRelationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 品牌
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */

@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.hasLength(key)) {
            wrapper.eq("brand_id", key).or().likeRight("name", key);
        }
        IPage<BrandEntity> page = this.page(new Query<BrandEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

    @Override
    public void updateCascade(BrandEntity brand) {
        super.updateById(brand);
        if (StringUtils.hasLength(brand.getName())) {
            CategoryBrandRelationEntity relation = new CategoryBrandRelationEntity();
            relation.setBrandId(brand.getBrandId());
            relation.setBrandName(brand.getName());
            UpdateWrapper<CategoryBrandRelationEntity> relationWrapper =
                new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id", brand.getBrandId());
            categoryBrandRelationService.update(relation, relationWrapper);
        }
    }
}
