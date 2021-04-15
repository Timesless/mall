package com.yangzl.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.Query;
import com.yangzl.mall.product.dao.AttrAttrgroupRelationDao;
import com.yangzl.mall.product.dao.AttrDao;
import com.yangzl.mall.product.dao.AttrGroupDao;
import com.yangzl.mall.product.dao.CategoryDao;
import com.yangzl.mall.product.entity.AttrAttrgroupRelationEntity;
import com.yangzl.mall.product.entity.AttrEntity;
import com.yangzl.mall.product.entity.AttrGroupEntity;
import com.yangzl.mall.product.entity.CategoryEntity;
import com.yangzl.mall.product.enums.AttrEnum;
import com.yangzl.mall.product.service.AttrService;
import com.yangzl.mall.product.service.CategoryService;
import com.yangzl.mall.product.vo.AttrGroupRelationVO;
import com.yangzl.mall.product.vo.AttrRespVO;
import com.yangzl.mall.product.vo.AttrVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品属性
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */

@Service("attrService")
@Transactional(rollbackFor = Exception.class)
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Resource
    private CategoryService categoryService;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private AttrGroupDao attrGroupDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryAttrBaseInfoPage(Map<String, Object> params, Long catelogId) {
        // 基本属性 attr_type = 1
        params.put("attr_type", 1);
        return queryPage(params, catelogId);
    }

    @Override
    public PageUtils queryAttrSaleInfoPage(Map<String, Object> params, Long catelogId) {
        // 销售属性 attr_type = 0
        params.put("attr_type", 0);
        return null;
    }

    @Override
    public void saveAttr(AttrVO attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        super.save(attrEntity);
        if (Objects.equals(attr.getAttrType(), AttrEnum.ATTR_BASE.getCode())) {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrId(attr.getAttrId());
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            attrAttrgroupRelationDao.insert(relationEntity);
        }
    }

    @Override
    public AttrRespVO getAttrInfoById(Long attrId) {
        AttrEntity entity = super.getById(attrId);
        AttrRespVO vo = new AttrRespVO();
        BeanUtils.copyProperties(entity, vo);

        if (Objects.equals(entity.getAttrType(), AttrEnum.ATTR_BASE.getCode())) {
            AttrAttrgroupRelationEntity relationEntity = attrAttrgroupRelationDao
                .selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
            if (relationEntity != null) {
                vo.setAttrGroupId(relationEntity.getAttrGroupId());
                AttrGroupEntity groupEntity = attrGroupDao.selectById(relationEntity.getAttrGroupId());
                Optional.ofNullable(groupEntity).ifPresent(group -> vo.setGroupName(group.getAttrGroupName()));
            }
        }
        Long[] catelogPath = categoryService.findCategoryPath(entity.getCatelogId());
        vo.setCatelogPath(catelogPath);
        CategoryEntity categoryEntity = categoryDao.selectById(entity.getCatelogId());
        if (categoryEntity != null) {
            vo.setCatelogName(categoryEntity.getName());
        }

        return vo;
    }

    @Override
    public void updateCascade(AttrVO attr) {
        AttrEntity entity = new AttrEntity();
        BeanUtils.copyProperties(attr, entity);
        super.updateById(entity);
        if (Objects.equals(attr.getAttrType(), AttrEnum.ATTR_BASE.getCode())) {
            // 更新分组关联
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrId(attr.getAttrId());
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            UpdateWrapper<AttrAttrgroupRelationEntity> relationWrapper =
                new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId());
            attrAttrgroupRelationDao.update(relationEntity, relationWrapper);
        }
    }

    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> relationEntity = attrAttrgroupRelationDao
            .selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
        if (CollectionUtils.isEmpty(relationEntity)) {
            return Collections.emptyList();
        }
        List<Long> entityIds = relationEntity.stream()
            .map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());

        return super.listByIds(entityIds);
    }

    @Override
    public void deleteRelation(List<AttrGroupRelationVO> vos) {

        List<AttrAttrgroupRelationEntity> collect = vos.stream().map(vo -> {
            AttrAttrgroupRelationEntity rs = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(vo, rs);
            return rs;
        }).collect(Collectors.toList());

        // 批量删除
        attrAttrgroupRelationDao.deleteBatchRelation(collect);
    }

    /** TODO */
    @Override
    public PageUtils getNoRelationAttr(Long attrgroupId, Map<String, Object> params) {
        AttrGroupEntity groupEntity = attrGroupDao.selectById(attrgroupId);
        List<AttrGroupEntity> entities = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>()
            .eq("catelog_id", groupEntity.getCatelogId()).ne("attr_group_id", attrgroupId));
        List<Long> entityIds = entities.stream().map(AttrGroupEntity::getAttrGroupId).collect(Collectors.toList());
        List<AttrAttrgroupRelationEntity> groupIds = attrAttrgroupRelationDao
            .selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", entityIds));
        List<Long> attrIds = groupIds.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().eq("catelog_id", groupEntity.getCatelogId());
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

    // =============================================================================================
    // divide
    // =============================================================================================

    /**
     * TODO 将多次 i/o 尽量缩减为一次 i/o
     * 查询 attr 信息
     *
     * @param params params
     * @param catelogId categoryId
     * @return page
     */
    private PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        if (0 == catelogId) {
            return queryPage(params);
        }
        Integer attrType = (Integer) params.get("attr_type");
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>()
            .eq("catelog_id", catelogId).eq("attr_type", attrType);
        String key = (String) params.get("key");
        if (StringUtils.hasLength(key)) {
            wrapper.and(w -> w.eq("attr_id", key).or().like("attr_name", key));
        }
        IPage<AttrEntity> pageEntity = this.page(new Query<AttrEntity>().getPage(params), wrapper);
        // 封装额外的信息
        List<AttrRespVO> collect = pageEntity.getRecords().stream().map(attrEntity -> {
            AttrRespVO rs = new AttrRespVO();
            BeanUtils.copyProperties(attrEntity, rs);
            // 基本属性才设置
            if (AttrEnum.ATTR_BASE.getCode() == attrType) {
                Long attrGroupId = attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>()
                    .eq("attr_id", rs.getAttrId())).getAttrGroupId();
                if (null != attrGroupId) {
                    rs.setGroupName(attrGroupDao.selectById(attrGroupId).getAttrGroupName());
                }
            }
            CategoryEntity categoryEntity = categoryDao.selectById(rs.getCatelogId());
            if (null != categoryEntity) {
                rs.setCatelogName(categoryEntity.getName());
            }
            return rs;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(pageEntity);
        pageUtils.setList(collect);

        return pageUtils;
    }
}
