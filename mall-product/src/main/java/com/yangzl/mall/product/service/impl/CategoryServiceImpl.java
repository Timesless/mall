package com.yangzl.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.product.dao.CategoryDao;
import com.yangzl.mall.product.entity.CategoryEntity;
import com.yangzl.mall.product.service.CategoryService;
import org.springframework.stereotype.Service;

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
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

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
