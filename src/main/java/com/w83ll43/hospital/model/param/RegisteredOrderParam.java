package com.w83ll43.hospital.model.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisteredOrderParam implements Serializable {

    /**
     * 病人编号
     * 可以从 BaseContext 获取
     */
    private Long patientId;

    /**
     * 挂号科室
     */
    private Long departmentId;

    /**
     * 挂号医生
     */
    private Long doctorId;
}
