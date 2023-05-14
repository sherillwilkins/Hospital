package com.w83ll43.hospital.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName department
 */
@TableName(value ="department")
@Data
public class Department implements Serializable {
    /**
     * 科室编号
     */
    @TableId
    private Long id;

    /**
     * 科室名称
     */
    private String name;

    /**
     * 科室介绍
     */
    private String description;

    /**
     * 联系方式
     */
    private String telephone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}