package com.w83ll43.hospital.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 挂号单表
 * @TableName registered_order
 */
@TableName(value ="registered_order")
@Data
public class RegisteredOrder implements Serializable {
    /**
     * 挂号单编号
     */
    @TableId
    private Long id;

    /**
     * 病人编号
     */
    private Long patientId;

    /**
     * 挂号科室
     */
    private Long departmentId;

    /**
     * 挂号医生
     */
    private Long doctorId;

    /**
     * 挂号时间
     */
    private Date date;

    /**
     * 缴费单编号
     */
    private Long billId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}