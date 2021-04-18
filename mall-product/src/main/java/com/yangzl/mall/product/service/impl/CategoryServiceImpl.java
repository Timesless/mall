package com.yangzl.mall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 商品三级分类
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc
 */
@Slf4j
@Service("categoryService")
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    private static final String REDIS_LOCK_KEY = "catalogJsonLock";

    private static final String REDIS_UNLOCK_LUA =
        "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

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

    /**
     * TODO: 压测时 Redis Lettuce 客户端导致直接内存溢出：OutOfDirectMemoryError
     *      实际原因：Lettuce 使用 Netty 做网络通信，是 Lettuce 操作 Netty 代码 bug
     *      -XX:MaxDirectMemorySize 默认等于 -Xmx
     *
     *  解决方案：
     *      1. 使用 Jedis Client
     *      2. 升级 Lettuce Client，当前版 Lettuce 并未抛出异常
     *
     * @return map
     */
    @Override
    public Map<String, List<Category2Vo>> getCatalogJson() {
        /*
         * 1. Null 缓存
         * 2. 设置过期时间
         * 3. db 查询加锁
         */
        String catalogJson = stringRedisTemplate.opsForValue().get("catalogJson");
        if (StringUtils.hasLength(catalogJson)) {
            return JSON.parseObject(catalogJson, new TypeReference<Map<String, List<Category2Vo>>>(){});
        }

        return getCatalogJsonFromDB();
    }

    /**
     * 添加分布式锁，版本1
     *
     * @return map
     */
    public Map<String, List<Category2Vo>> getCatalogJsonWithDistributedLock1() {
        String uuid = UUID.randomUUID().toString();
        // SET K V NX EX 30
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_KEY, uuid, 30, TimeUnit.SECONDS);
        if (flag) {
            Map<String, List<Category2Vo>> rs = getCatalogJsonFromDB();
            /*
             * 1. 解锁，确保删除自己上的锁
             * 2. 非原子性的
             *  如何解决：
             *      2.1 事务
             *      2.2 lua
             *      2.3 redisson
             * 3. 锁续期问题
             *    解决：
             *      redisson
             */
            /*String lockUUID = stringRedisTemplate.opsForValue().get(DISTRIBUTE_LOCK_KEY);
            if (uuid.equals(lockUUID)) {
                stringRedisTemplate.delete(DISTRIBUTE_LOCK_KEY);
            }*/
            stringRedisTemplate.execute(
                new DefaultRedisScript<>(REDIS_UNLOCK_LUA), Collections.singletonList(REDIS_LOCK_KEY), uuid);

            return rs;
        }
        // 休眠一小段时间后重试
        try { TimeUnit.MILLISECONDS.sleep(60); } catch (InterruptedException e) { e.printStackTrace(); }

        return getCatalogJsonWithDistributedLock1();
    }

    /**
     * 从 db 获取数据，加锁解决缓存穿透
     *      1. 本地锁
     *      2. 分布式锁
     *
     *  本地锁是更好的解决方案，因为我们的目标是降低 DB 的访问，本地锁已经能过滤 95% 以上的无效请求了
     * @return map
     */
    synchronized public Map<String, List<Category2Vo>> getCatalogJsonFromDB() {

        // 获取锁之后首先判断是否已经有缓存数据了
        String catalogJson = stringRedisTemplate.opsForValue().get("catalogJson");
        if (StringUtils.hasLength(catalogJson)) {
            return JSON.parseObject(catalogJson, new TypeReference<Map<String, List<Category2Vo>>>(){});
        }

        log.info("......未命中缓存，执行 DB 查询");
        // 预先查询所有分类数据，在内存操作
        List<CategoryEntity> list = baseMapper.selectList(new QueryWrapper<>());
        List<CategoryEntity> lv1 = list.stream().filter(e -> 0 == e.getParentCid()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(lv1)) {
            return Collections.emptyMap();
        }
        Map<String, List<Category2Vo>> rs = lv1.stream()
            .collect(Collectors.toMap(entity -> entity.getCatId().toString(), e1 -> {
                String e1Id = e1.getCatId().toString();

                // 获取 2 级分类 list
                List<CategoryEntity> lv2 = list.stream()
                    .filter(entity -> e1.getCatId().equals(entity.getParentCid())).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(lv2)) {
                    return Collections.emptyList();
                }

                // 组装二级分类数据
                List<Category2Vo> l2vo = lv2.stream().map(e2 -> {
                    String e2Id = e2.getCatId().toString();
                    Category2Vo category2Vo = new Category2Vo(e1Id, null, e2.getCatId().toString(), e2.getName());
                    // 为 2 级分类设置 3 级分类
                    List<CategoryEntity> lv3 = list.stream()
                        .filter(entity -> e2.getCatId().equals(entity.getParentCid())).collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(lv3)) {
                        return category2Vo;
                    }
                    List<Category3Vo> l3vo = lv3.stream()
                        .map(e3 -> new Category3Vo(e2Id, e3.getCatId().toString(), e3.getName())).collect(Collectors.toList());
                    category2Vo.setCatalog3List(l3vo);

                    return category2Vo;
                }).collect(Collectors.toList());

                return l2vo;
            }, (ov, cv) -> cv));
        // 放入缓存
        stringRedisTemplate.opsForValue().set("catalogJson", JSON.toJSONString(rs));

        return rs;
    }

    // ======================================================================================
    // divide line
    // ======================================================================================

    /**
     * 获取下一级的分类数据
     *
     * @param list 所有分类数据
     * @param parentId cid
     * @return list
     */
    private List<CategoryEntity> getNextLevelCategory(List<CategoryEntity> list, Long parentId) {

        return list.stream().filter(entity -> parentId.equals(entity.getParentCid())).collect(Collectors.toList());
    }

    /**
     * 为当前分类递归地的设置子分类
     *
     * @param curLevel 当前类
     * @param list     分类列表
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
