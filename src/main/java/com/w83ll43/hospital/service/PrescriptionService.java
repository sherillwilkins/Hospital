package com.w83ll43.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.hospital.model.domain.Prescription;
import com.w83ll43.hospital.model.param.PrescriptionParam;
import com.w83ll43.hospital.model.vo.FeeDetail;
import com.w83ll43.hospital.model.vo.PrescriptionVo;

import java.util.List;

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

    /**
     * 获取处方单费用明细
     * @param billId
     * @return
     */
    List<FeeDetail> getPrescriptionFeeDetails(Long billId);

    List<PrescriptionVo> getPrescriptionVos();

    /**
     * 根据处方单编号缴费
     * @param id
     * @return
     */
    Boolean payWithPrescription(Long id);
}
