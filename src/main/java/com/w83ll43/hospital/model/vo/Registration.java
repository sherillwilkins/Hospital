package com.w83ll43.hospital.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Registration implements Serializable {

    /**
     * 挂号单编号
     */
    private Long id;

    /**
     * 病人姓名
     */
    private String patientName;

    /**
     * 挂号科室
     */
    private String departmentName;

    /**
     * 挂号医生
     */
    private String doctorName;

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
