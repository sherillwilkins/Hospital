package com.w83ll43.hospital.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 缴费单表
 * @TableName bill
 */
@TableName(value ="bill")
@Data
public class Bill implements Serializable {
    /**
     * 缴费单编号
     */
    @TableId
    private Long id;

    /**
     * 缴费病人
     */
    private Long patientId;

    /**
     * 缴费类型
     */
    private Integer type;

    /**
     * 缴费金额
     */
    private BigDecimal amount;

    /**
     * 缴费状态
     */
    private Integer status;

    /**
     * 缴费日期
     */
    private Date date;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}