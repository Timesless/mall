package com.yangzl.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * spu信息
 *
 * @author yangzl
 * @date 2020/11/19 20:27:08
 * @desc SPU 与 SKU
 *
 *  SPU「Standard Product Unit」，标准化产品单元，SPU 描述了一个产品的特性
 *  SKU「Stock Keeping Unit」，库存量单元
 *
 *  iphone x，mi 8 是 spu
 *  iphone x 64G 黑曜石是 sku
 *  mi 8 64G + 黑是 sku
 *
 *  所有 sku 共享同一个 spu
 *
 *  spu 对应 规格参数
 *  sku 对应 销售参数
 */
@Data
@TableName("pms_spu_info")
public class SpuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId
	private Long id;
	/**
	 * 商品名称
	 */
	private String spuName;
	/**
	 * 商品描述
	 */
	private String spuDescription;
	/**
	 * 所属分类id
	 */
	private Long catalogId;
	/**
	 * 品牌id
	 */
	private Long brandId;
	/**
	 *
	 */
	private BigDecimal weight;
	/**
	 * 上架状态[0 - 下架，1 - 上架]
	 */
	private Integer publishStatus;
	/**
	 *
	 */
	private Date createTime;
	/**
	 *
	 */
	private Date updateTime;

}
