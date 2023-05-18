package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.common.BaseContext;
import com.w83ll43.hospital.common.CustomException;
import com.w83ll43.hospital.mapper.PrescriptionMapper;
import com.w83ll43.hospital.model.domain.Prescription;
import com.w83ll43.hospital.model.domain.User;
import com.w83ll43.hospital.model.param.PrescriptionDrugParam;
import com.w83ll43.hospital.model.param.PrescriptionParam;
import com.w83ll43.hospital.service.BillService;
import com.w83ll43.hospital.service.PrescriptionDrugItemService;
import com.w83ll43.hospital.service.PrescriptionService;
import com.w83ll43.hospital.service.UserService;
import com.w83ll43.hospital.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author w83ll43
* @description 针对表【prescription(处方表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionMapper, Prescription>
    implements PrescriptionService{

    @Resource
    private UserService userService;

    @Resource
    private BillService billService;

    @Resource
    private PrescriptionDrugItemService prescriptionDrugItemService;

    /**
     * 创建处方
     * @param prescriptionParam
     * @return
     */
    @Override
    public Prescription createPrescription(PrescriptionParam prescriptionParam) {
        // 1、获取登录用户的 ID
        Long id = BaseContext.getCurrentId();

        // 2、获取登录用户
        User user = userService.getById(id);

        // 3、判断用户角色
        if (user.getRole() != "D") {
            throw new CustomException("非医生用户不能创建处方！");
        }

        // 4、创建处方实体
        Prescription prescription = new Prescription();

        // 5、设置处方属性
        Long patientId = prescriptionParam.getPatientId();
        prescription.setPatientId(patientId);
        prescription.setDoctorId(id);
        prescription.setSymptom(prescription.getSymptom());

        Date currentDate = DateUtils.getCurrentDate();
        prescription.setDate(currentDate);

        // 6、生成处方 ID 值
        long prescriptionId = IdWorker.getId(prescription);
        prescription.setId(prescriptionId);

        // 7、保存至数据库 防止添加药品项时外键不存在
        this.save(prescription);

        // 8、创建药品项
        List<PrescriptionDrugParam> prescriptionDrugParamList = prescriptionParam.getPrescriptionDrugParamList();
        prescriptionDrugItemService.createDrugItems(prescriptionId, prescriptionDrugParamList);

        // 9、生成缴费单
        Long billId = billService.createPrescriptionBill(patientId, id, prescriptionDrugParamList, currentDate);
        prescription.setBillId(billId);

        // 10、更新数据库中的处方缴费单编号
        this.updateById(prescription);

        return prescription;
    }
}




