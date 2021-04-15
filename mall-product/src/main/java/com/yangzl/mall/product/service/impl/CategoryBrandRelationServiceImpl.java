package com.yangzl.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.product.dao.BrandDao;
import com.yangzl.mall.product.dao.CategoryBrandRelationDao;
import com.yangzl.mall.product.dao.CategoryDao;
import com.yangzl.mall.product.entity.BrandEntity;
import com.yangzl.mall.product.entity.CategoryBrandRelationEntity;
import com.yangzl.mall.product.service.CategoryBrandRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */

@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Resource
    private BrandDao brandDao;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        String brandName = brandDao.selectById(categoryBrandRelation.getBrandId()).getName();
        String categoryName = categoryDao.selectById(categoryBrandRelation.getCatelogId()).getName();
        categoryBrandRelation.setBrandName(brandName);
        categoryBrandRelation.setCatelogName(categoryName);

        super.save(categoryBrandRelation);
    }

    @Override
    public List<BrandEntity> getBrandByCatId(Long catId) {

        List<CategoryBrandRelationEntity> relationEntities = categoryBrandRelationDao
            .selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
        return null;
    }
}
