package com.w83ll43.hospital.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 住院档案
 * @TableName hospitalization_file
 */
@TableName(value ="hospitalization_file")
@Data
public class HospitalizationFile implements Serializable {
    /**
     * 住院档案编号
     */
    @TableId
    private Long id;

    /**
     * 病人编号
     */
    private Long patientId;

    /**
     * 入院时间
     */
    private Date admissionTime;

    /**
     * 出院时间
     */
    private Date dischargeTime;

    /**
     * 病床编号
     */
    private Long bedId;

    /**
     * 预缴费单编号
     */
    private Long billId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}