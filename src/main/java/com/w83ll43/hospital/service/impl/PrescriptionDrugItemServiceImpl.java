package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.mapper.PrescriptionDrugItemMapper;
import com.w83ll43.hospital.model.domain.PrescriptionDrugItem;
import com.w83ll43.hospital.model.param.PrescriptionDrugParam;
import com.w83ll43.hospital.service.PrescriptionDrugItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author w83ll43
* @description 针对表【prescription_drug_item(处方药品项记录表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:29
*/
@Service
public class PrescriptionDrugItemServiceImpl extends ServiceImpl<PrescriptionDrugItemMapper, PrescriptionDrugItem>
    implements PrescriptionDrugItemService{

    /**
     * 添加药品项记录
     * @param prescriptionId
     * @param prescriptionDrugParamList
     */
    @Override
    public void createDrugItems(long prescriptionId, List<PrescriptionDrugParam> prescriptionDrugParamList) {
        for (PrescriptionDrugParam prescriptionDrugParam : prescriptionDrugParamList) {
            // 1、新建药品项实体
            PrescriptionDrugItem drugItem = new PrescriptionDrugItem();

            // 2、将 prescriptionDrugParam 的属性值拷贝到 drugItem 中
            BeanUtils.copyProperties(prescriptionDrugParam, drugItem);

            // 3、设置处方编号
            drugItem.setPrescriptionId(prescriptionId);

            // 4、保存至数据库
            this.save(drugItem);
        }
    }
}




