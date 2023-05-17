package com.w83ll43.hospital.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 住院记录
 * @TableName hospitalization_record
 */
@TableName(value ="hospitalization_record")
@Data
public class HospitalizationRecord implements Serializable {
    /**
     * 住院记录编号
     */
    @TableId
    private Long id;

    /**
     * 住院档案编号
     */
    private Long fileId;

    /**
     * 病人症状
     */
    private String symptom;

    /**
     * 治疗方案
     */
    private String treatment;

    /**
     * 记录的日期 
指的是记录的内容发生在某天
     */
    private Date recordTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}