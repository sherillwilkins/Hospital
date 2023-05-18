package com.w83ll43.hospital.model.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PrescriptionParam implements Serializable {

    /**
     * 病人编号
     */
    private Long patientId;

    /**
     * 症状描述
     */
    private String symptom;

    /**
     * 是否住院
     */
    private Integer hospitalized;

    /**
     * 用药清单
     */
    private List<PrescriptionDrugParam> prescriptionDrugParamList;

}
