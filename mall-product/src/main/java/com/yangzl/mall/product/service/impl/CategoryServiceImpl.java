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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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
