package com.w83ll43.hospital.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Registration implements Serializable {

    /**
     * 挂号单编号
     */
    @TableId
    private Long id;

    /**
     * 病人编号
     */
    private Long patientName;

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
     * 缴费金额
     */
    private BigDecimal amount;

    /**
     * 缴费状态
     */
    private Integer status;
}
