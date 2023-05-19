package com.w83ll43.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.hospital.model.domain.Bill;
import com.w83ll43.hospital.model.domain.Drug;
import com.w83ll43.hospital.model.param.PrescriptionDrugParam;

import java.util.Date;
import java.util.List;

/**
* @author w83ll43
* @description 针对表【bill(缴费单表)】的数据库操作Service
* @createDate 2023-05-17 22:33:28
*/
public interface BillService extends IService<Bill> {

    /**
     * 创建挂号单缴费单
     * @param patientId
     * @param billDate
     * @return
     */
    Long createRegistrationBill(Long patientId, Date billDate);

    /**
     * 创建处方缴费单
     * @param patientId
     * @param doctorId
     * @param prescriptionDrugParamList
     * @param billDate
     * @return
     */
    Long createPrescriptionBill(Long patientId, Long doctorId, List<PrescriptionDrugParam> prescriptionDrugParamList, Date billDate);

    /**
     * 缴费
     * @param id
     * @return
     */
    Boolean payBill(Long id);

    /**
     * 根据 id 获取药品信息
     * @param drugId
     * @return
     */
    Drug getDrugById(Long drugId);
}
