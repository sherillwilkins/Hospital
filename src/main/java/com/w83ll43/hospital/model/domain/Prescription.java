package com.w83ll43.hospital.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 处方表
 * @TableName prescription
 */
@TableName(value ="prescription")
@Data
public class Prescription implements Serializable {
    /**
     * 处方编号
     */
    @TableId
    private Long id;

    /**
     * 病人编号
     */
    private Long patientId;

    /**
     * 医生编号
     */
    private Long doctorId;

    /**
     * 症状描述
     */
    private String symptom;

    /**
     * 开具日期
     */
    private Date date;

    /**
     * 是否住院
     */
    private Integer hospitalized;

    /**
     * 缴费单编号
     */
    private Long billId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}