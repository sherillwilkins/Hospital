package com.w83ll43.hospital.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName patient
 */
@TableName(value ="patient")
@Data
public class Patient implements Serializable {
    /**
     * 病人编号
     */
    @TableId
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别--1为男、2为女
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 联系方式
     */
    private String telephone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}