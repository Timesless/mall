package com.yangzl.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.product.dao.AttrAttrgroupRelationDao;
import com.yangzl.mall.product.dao.AttrGroupDao;
import com.yangzl.mall.product.entity.AttrAttrgroupRelationEntity;
import com.yangzl.mall.product.entity.AttrEntity;
import com.yangzl.mall.product.entity.AttrGroupEntity;
import com.yangzl.mall.product.service.AttrGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 属性分组
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */

@Service("attrGroupService")
@Transactional(rollbackFor = Exception.class)
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        // 查询所有
        if (0 == catelogId) {
            return  queryPage(params);
        }
        // 按照三级分类查
        String key = (String) params.get("key");
        // select .. pms_attr_group where catelog_id = ? and (attr_group_name like ? or attr_group_desc like ?)
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId);
        if (StringUtils.hasLength(key)) {
            wrapper.and(w -> w.likeRight("attr_group_name", key).or().likeRight("attr_group_desc", key));
        }
        IPage<AttrGroupEntity> page  = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

}
