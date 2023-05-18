package com.w83ll43.hospital.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门表
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
     * 所属部门
     */
    private Long sectionId;

    /**
     * 联系方式
     */
    private String telephone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}