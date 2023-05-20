package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.common.BaseContext;
import com.w83ll43.hospital.common.CustomException;
import com.w83ll43.hospital.mapper.PrescriptionMapper;
import com.w83ll43.hospital.model.domain.*;
import com.w83ll43.hospital.model.param.PrescriptionDrugParam;
import com.w83ll43.hospital.model.param.PrescriptionParam;
import com.w83ll43.hospital.model.vo.FeeDetail;
import com.w83ll43.hospital.model.vo.PrescriptionVo;
import com.w83ll43.hospital.service.*;
import com.w83ll43.hospital.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    private PatientService patientService;

    @Resource
    private DoctorService doctorService;

    @Resource
    private PrescriptionDrugItemService prescriptionDrugItemService;

    /**
     * 创建处方
     * @param prescriptionParam
     * @return
     */
    @Override
    public Prescription createPrescription(PrescriptionParam prescriptionParam) {
        long startTime = System.currentTimeMillis();

        // 1、获取登录用户的 ID
        Long id = BaseContext.getCurrentId();

        // 2、获取登录用户
        User user = userService.getById(id);

        // 3、判断用户角色
        if (!"D".equals(user.getRole())) {
            throw new CustomException("非医生用户不能创建处方！");
        }

        // 4、创建处方实体
        Prescription prescription = new Prescription();

        // 5、设置处方属性
        Long patientId = prescriptionParam.getPatientId();
        prescription.setPatientId(patientId);
        prescription.setDoctorId(id);
        prescription.setSymptom(prescriptionParam.getSymptom());
        prescription.setHospitalized(prescriptionParam.getHospitalized());

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

        System.out.println("执行时间为：" + (System.currentTimeMillis() - startTime));
        return prescription;
    }

    /**
     * 获取处方单费用明细
     * @param billId
     * @return
     */
    @Override
    public List<FeeDetail> getPrescriptionFeeDetails(Long billId) {
        // 1、根据缴费单编号查询处方单
        LambdaQueryWrapper<Prescription> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Prescription::getBillId, billId);

        Prescription prescription = this.getOne(queryWrapper);
        // 获取处方单的医生编号 用于计算诊疗费用
        Long patientId = prescription.getPatientId();
        Long doctorId = prescription.getDoctorId();

        // 2、根据处方单编号查询处方药品项编号
        LambdaQueryWrapper<PrescriptionDrugItem> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(PrescriptionDrugItem::getPrescriptionId, prescription.getId());

        List<PrescriptionDrugItem> drugItems = prescriptionDrugItemService.list(lambdaQueryWrapper);

        // 3、构建费用明细 VO
        List<FeeDetail> feeDetails = new ArrayList<>();
        Bill bill = billService.getById(billId);
        for (PrescriptionDrugItem drugItem : drugItems) {
            // 获取药品项编号
            Long drugId = drugItem.getDrugId();

            // 获取药品项价格
            // 使用 billService 获取 Drug 避免循环依赖注入
            Drug drug = billService.getDrugById(drugId);
            FeeDetail feeDetail = new FeeDetail();
            feeDetail.setName(drug.getName());
            feeDetail.setType("药品费用");
            feeDetail.setPrice(drug.getPrice());
            Date date = bill.getDate();
            feeDetail.setDate(date);

            // 添加到费用明细列表中
            feeDetails.add(feeDetail);
        }

        // 4、医生诊疗费
        FeeDetail feeDetail = new FeeDetail();
        // TODO 根据医生编号查询医生诊疗费用
        feeDetail.setName("医生诊疗费");
        feeDetail.setPrice(new BigDecimal(doctorId));
        feeDetail.setType("诊疗费");
        feeDetail.setDate(bill.getDate());

        feeDetails.add(feeDetail);
        return feeDetails;
    }


    public PrescriptionVo getPrescriptionVoByBillId(Long billId) {
        PrescriptionVo prescriptionVo = new PrescriptionVo();

        LambdaQueryWrapper<Prescription> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Prescription::getBillId, billId);

        Prescription prescription = this.getOne(lambdaQueryWrapper);
        prescriptionVo.setId(prescription.getId());

        Patient patient = patientService.getById(prescription.getPatientId());
        prescriptionVo.setPatientName(patient.getName());

        Doctor doctor = doctorService.getById(prescription.getDoctorId());
        prescriptionVo.setDoctorName(doctor.getName());

        prescriptionVo.setSymptom(prescription.getSymptom());
        prescriptionVo.setDate(prescription.getDate());
        prescriptionVo.setHospitalized(prescription.getHospitalized());

        Bill bill = billService.getById(billId);
        prescriptionVo.setBillId(billId);
        prescriptionVo.setAmount(bill.getAmount());
        prescriptionVo.setStatus(bill.getStatus());

        return prescriptionVo;
    }

    @Override
    public List<PrescriptionVo> getPrescriptionVos() {
        List<PrescriptionVo> prescriptionVos = new ArrayList<>();

        LambdaQueryWrapper<Bill> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // TODO 处方单类型
        lambdaQueryWrapper.eq(Bill::getType, 2);

        List<Bill> bills = billService.list(lambdaQueryWrapper);
        for (Bill bill : bills) {
            PrescriptionVo prescriptionVo = getPrescriptionVoByBillId(bill.getId());
            prescriptionVos.add(prescriptionVo);
        }

        return prescriptionVos;
    }
}




