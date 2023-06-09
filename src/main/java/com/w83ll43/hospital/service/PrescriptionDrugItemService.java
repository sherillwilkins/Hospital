package com.w83ll43.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.hospital.model.domain.PrescriptionDrugItem;
import com.w83ll43.hospital.model.param.PrescriptionDrugParam;

import java.util.List;

/**
* @author w83ll43
* @description 针对表【prescription_drug_item(处方药品项记录表)】的数据库操作Service
* @createDate 2023-05-17 22:33:29
*/
public interface PrescriptionDrugItemService extends IService<PrescriptionDrugItem> {

    /**
     * 添加药品项记录
     * @param prescriptionId
     * @param prescriptionDrugParamList
     */
    void createDrugItems(long prescriptionId, List<PrescriptionDrugParam> prescriptionDrugParamList);
}
