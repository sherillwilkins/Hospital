package com.w83ll43.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.hospital.model.domain.Prescription;
import com.w83ll43.hospital.model.param.PrescriptionParam;

/**
* @author w83ll43
* @description 针对表【prescription(处方表)】的数据库操作Service
* @createDate 2023-05-17 22:33:28
*/
public interface PrescriptionService extends IService<Prescription> {

    /**
     * 创建处方
     * @param prescriptionParam
     * @return
     */
    Prescription createPrescription(PrescriptionParam prescriptionParam);
}
