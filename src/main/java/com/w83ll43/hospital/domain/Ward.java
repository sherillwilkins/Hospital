package com.w83ll43.hospital.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 病房表
 * @TableName ward
 */
@TableName(value ="ward")
@Data
public class Ward implements Serializable {
    /**
     * 病房编号
     */
    @TableId
    private Long id;

    /**
     * 病房位置
     */
    private String location;

    /**
     * 所属科室
     */
    private Long departmentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}