package com.yangzl.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.product.dao.ProductAttrValueDao;
import com.yangzl.mall.product.entity.ProductAttrValueEntity;
import com.yangzl.mall.product.service.ProductAttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * spu属性值
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */

@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<ProductAttrValueEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public List<ProductAttrValueEntity> selectSearchAttrIds(Long spuId) {
        List<ProductAttrValueEntity> list =
            this.list(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", spuId).eq("search_type", 1));

        return list;
    }
}
