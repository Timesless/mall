package com.yangzl.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.product.dao.CategoryDao;
import com.yangzl.mall.product.entity.CategoryBrandRelationEntity;
import com.yangzl.mall.product.entity.CategoryEntity;
import com.yangzl.mall.product.service.CategoryBrandRelationService;
import com.yangzl.mall.product.service.CategoryService;
import com.yangzl.mall.product.vo.Category2Vo;
import com.yangzl.mall.product.vo.Category3Vo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品三级分类
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
@Service("categoryService")
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listForTree() {
        List<CategoryEntity> list = super.list();
        List<CategoryEntity> level1 = list.stream()
            .filter(category -> category.getParentCid() == 0).collect(Collectors.toList());
        setChildrenRecursivly(level1, list);

        return level1;
    }

    @Override
    public void updateCascade(CategoryEntity category) {
        super.updateById(category);
        // 更新的字段中有 name 字段
        if (StringUtils.hasLength(category.getName())) {
            CategoryBrandRelationEntity relation = new CategoryBrandRelationEntity();
            relation.setCatelogId(category.getCatId());
            relation.setCatelogName(category.getName());
            UpdateWrapper<CategoryBrandRelationEntity> relationWrapper =
                new UpdateWrapper<CategoryBrandRelationEntity>().eq("category_id", category.getCatId());
            categoryBrandRelationService.update(relation, relationWrapper);
        }
    }

    @Override
    public Long[] findCategoryPath(Long catelogId) {
        // TODO
        return new Long[0];
    }

    @Override
    public List<CategoryEntity> getLevel1Categories() {

        return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
    }

    @Override
    public Map<String, List<Category2Vo>> getCatalogJson() {
        /*
         * 1. 查询 1 级分类
         * 2. 封装数据
         */
        List<CategoryEntity> level1 = this.getLevel1Categories();
        if (CollectionUtils.isEmpty(level1)) {
            return Collections.<String, List<Category2Vo>>emptyMap();
        }
        Map<String, List<Category2Vo>> list = level1.stream().collect(Collectors.toMap(e1 -> e1.getCatId().toString(), e -> {
            // v 是一级分类，要查询 1 级分类下的所有 2 级分类
            List<CategoryEntity> l2Entity = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", e.getCatId()));
            if (CollectionUtils.isEmpty(l2Entity)) {
                return null;
            }
            List<Category2Vo> vo2 = l2Entity.stream().map(e2 -> {
                Category2Vo l2vo = new Category2Vo(e.getCatId().toString(), null, e2.getCatId().toString(), e2.getName());
                // 二级分类查询三级分类
                List<CategoryEntity> l3Entity = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", l2vo.getCatalogId()));
                if (CollectionUtils.isEmpty(l3Entity)) {
                    return null;
                }
                List<Category3Vo> l3vo = l3Entity.stream().map(e3 -> {
                    Category3Vo vo = new Category3Vo(l2vo.getCatalogId(), e3.getCatId().toString(), e3.getName());

                    return vo;
                }).collect(Collectors.toList());
                l2vo.setCatalog3List(l3vo);
                return l2vo;
            }).collect(Collectors.toList());

            return vo2;
        }, (ov, cv) -> ov));

        return list;
    }

    // ======================================================================================
    // divide line
    // ======================================================================================

    /**
     * 为当前分类递归地的设置子分类
     *
     * @param curLevel 当前类
     * @param list 分类列表
     */
    private void setChildrenRecursivly(List<CategoryEntity> curLevel, List<CategoryEntity> list) {
        curLevel.forEach(curr -> {
            List<CategoryEntity> nextLevel = list.stream()
                .filter(category -> curr.getCatId().equals(category.getParentCid())).collect(Collectors.toList());
            curr.setChildren(nextLevel);
            setChildrenRecursivly(nextLevel, list);
        });
    }
}
