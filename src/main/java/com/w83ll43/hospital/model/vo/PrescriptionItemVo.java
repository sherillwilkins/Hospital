package com.w83ll43.hospital.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PrescriptionItemVo implements Serializable {

    /**
     * 药品名称
     */
    private String drugName;

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

    /**
     * 开具日期
     */
    private Date date;

    /**
     * 价格
     */
    private BigDecimal price;

}