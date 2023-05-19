package com.w83ll43.hospital.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class FeeDetail implements Serializable {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目类型
     * 药品、诊疗费用、挂号费
     */
    private String type;

    /**
     * 项目金额
     */
    private BigDecimal price;

    /**
     * 收费时间
     */
    private Date date;
}
