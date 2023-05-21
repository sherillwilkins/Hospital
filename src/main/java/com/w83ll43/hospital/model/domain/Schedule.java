package com.w83ll43.hospital.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 值班表
 * @TableName schedule
 */
@TableName(value ="schedule")
@Data
public class Schedule implements Serializable {
    /**
     * 值班编号
     */
    @TableId
    private Long id;

    /**
     * 值班医生
     */
    private Long doctorId;

    /**
     * 值班地点
     */
    private String place;

    /**
     * 值班科室
     */
    private Long departmentId;

    /**
     * 值班开始时间
     */
    private Date startTime;

    /**
     * 值班结束时间
     */
    private Date endTime;

    /**
     * 值班类型
     */
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}