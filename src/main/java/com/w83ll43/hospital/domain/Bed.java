package com.w83ll43.hospital.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 病床表
 * @TableName bed
 */
@TableName(value ="bed")
@Data
public class Bed implements Serializable {
    /**
     * 病床编号
     */
    @TableId
    private Long id;

    /**
     * 病房编号
     */
    private Long wardId;

    /**
     * 病床床位费
     */
    private BigDecimal charge;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}