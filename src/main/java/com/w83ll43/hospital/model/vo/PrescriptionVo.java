package com.w83ll43.hospital.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PrescriptionVo implements Serializable {
    /**
     * 处方编号
     */
    private Long id;

    /**
     * 病人姓名
     */
    private String patientName;

    /**
     * 医生姓名
     */
    private String doctorName;

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
     * 缴费金额
     */
    private BigDecimal amount;

    /**
     * 缴费状态
     */
    private Integer status;

    /**
     * 缴费单编号
     */
    private Long billId;

}