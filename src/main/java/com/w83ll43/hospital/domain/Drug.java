package com.w83ll43.hospital.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 药品表
 * @TableName drug
 */
@TableName(value ="drug")
@Data
public class Drug implements Serializable {
    /**
     * 药品编号
     */
    @TableId
    private Long id;

    /**
     * 药品名称
     */
    private String name;

    /**
     * 药品规格
     */
    private String specification;

    /**
     * 药品单价
     */
    private BigDecimal price;

    /**
     * 价格单位
     */
    private String unit;

    /**
     * 库存数量
     */
    private Integer stack;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}