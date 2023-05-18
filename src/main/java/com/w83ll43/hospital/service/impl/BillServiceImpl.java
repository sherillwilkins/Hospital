package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.mapper.BillMapper;
import com.w83ll43.hospital.model.domain.Bill;
import com.w83ll43.hospital.model.domain.Drug;
import com.w83ll43.hospital.model.param.PrescriptionDrugParam;
import com.w83ll43.hospital.service.BillService;
import com.w83ll43.hospital.service.DrugService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* @author w83ll43
* @description 针对表【bill(缴费单表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill>
    implements BillService{

    @Resource
    private DrugService drugService;

    /**
     * 创建挂号单缴费单
     * @param patientId
     * @param billDate
     * @return
     */
    @Override
    public Long createRegistrationBill(Long patientId, Date billDate) {
        // 1、创建空实体
        Bill bill = new Bill();

        // 2、设置实体属性
        bill.setPatientId(patientId);
        // TODO 缴费单类型常量值
        bill.setType(1);

        // TODO 挂号单费用常量值
        bill.setAmount(BigDecimal.valueOf(100));
        bill.setDate(billDate);

        // 3、生成实体ID
        long billId = IdWorker.getId(bill);
        bill.setId(billId);

        // 4、保存到数据库中
        this.save(bill);
        return billId;
    }

    /**
     * 创建处方缴费单
     * @param patientId
     * @param doctorId
     * @param prescriptionDrugParamList
     * @param billDate
     * @return
     */
    @Override
    public Long createPrescriptionBill(Long patientId, Long doctorId, List<PrescriptionDrugParam> prescriptionDrugParamList, Date billDate) {
        // 2、创建缴费单实体
        Bill bill = new Bill();

        // 3、设置实体属性
        bill.setPatientId(patientId);
        // TODO 缴费单类型常量值
        bill.setType(2);
        bill.setDate(billDate);

        // 3、获取医生诊疗费用
        // TODO 医生增加属性——诊疗非费用
        BigDecimal amount = new BigDecimal("28.88");

        // 4、计算所有药品项金额总和
        for (PrescriptionDrugParam prescriptionDrugParam : prescriptionDrugParamList) {
            Long drugId = prescriptionDrugParam.getDrugId();

            // 获取药品实体
            // TODO 实际上不同药品的规格的费用不同 需要根据规矩进行计算
            Drug drug = drugService.getById(drugId);
            amount = amount.add(drug.getPrice());
        }
        bill.setAmount(amount);

        // 5、生成缴费单编号
        long billId = IdWorker.getId(bill);
        bill.setId(billId);

        // 6、保存至数据库
        this.save(bill);

        return billId;
    }
}




