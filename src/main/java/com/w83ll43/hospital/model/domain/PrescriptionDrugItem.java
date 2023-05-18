package com.w83ll43.hospital.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 处方药品项记录表
 * @TableName prescription_drug_item
 */
@TableName(value ="prescription_drug_item")
@Data
public class PrescriptionDrugItem implements Serializable {
    /**
     * 处方药品项编号
     */
    @TableId
    private Long id;

    /**
     * 处方编号
     */
    private Long prescriptionId;

    /**
     * 处方药品编号
     */
    private Long drugId;

    /**
     * 处方药品份量（剂型）
     */
    private String dosage;

    /**
     * 处方药品服用次数（服用频率）
     * 如 每日两次
     */
    private String frequency;

    /**
     * 处方药品服用数量
     * 如 2
     */
    private Integer usageNum;

    /**
     * 处方药品服用单位
     * 如粒、毫升、瓶等
     */
    private String usageUnit;

    /**
     * 处方药品服用方式
     * 如口服、饭后服、外服等
     */
    private String usageMethod;

    /**
     * 处方药品使用时长（持续时间）
     * 如 一星期
     */
    private String duration;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}