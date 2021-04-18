package com.yangzl.mall.product.controller;

import com.yangzl.common.utils.PageUtils;
import com.yangzl.common.utils.R;
import com.yangzl.mall.product.entity.AttrEntity;
import com.yangzl.mall.product.entity.AttrGroupEntity;
import com.yangzl.mall.product.service.AttrGroupService;
import com.yangzl.mall.product.service.AttrService;
import com.yangzl.mall.product.vo.AttrGroupRelationVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 属性分组
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Resource
    private AttrGroupService attrGroupService;
    @Resource
    private AttrService attrService;

    // /product/attrgroup/{attrgourpId}/attr/relation
    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable Long attrgroupId) {
        List<AttrEntity> list = attrService.getRelationAttr(attrgroupId);

        return R.ok().put("data", list);
    }

    // /product/attrgroup/{attrgroupId}/noattr/relation
    @GetMapping("/{attrgroupId}/attr/noRelation")
    public R attrNoRelation(@PathVariable Long attrgroupId,
                            @RequestParam Map<String, Object> params) {
        PageUtils page = attrService.getNoRelationAttr(attrgroupId, params);

        return R.ok().put("page", page);
    }

    // /product/attrgroup/attr/relation/delete
    @DeleteMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody List<AttrGroupRelationVO> vos) {
        attrService.deleteRelation(vos);

        return R.ok();
    }

    /**
     * 根据分类查询列表
     */
    @RequestMapping("/list/{catelogId}")
    public R listByCatelogId(@RequestParam Map<String, Object> params,
                             @PathVariable Long catelogId) {
        PageUtils page = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("data", page);
    }

    // ========

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrGroupService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
