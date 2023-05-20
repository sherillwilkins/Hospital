package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.common.BaseContext;
import com.w83ll43.hospital.common.CustomException;
import com.w83ll43.hospital.mapper.RegisteredOrderMapper;
import com.w83ll43.hospital.model.domain.*;
import com.w83ll43.hospital.model.param.RegisteredOrderParam;
import com.w83ll43.hospital.model.vo.FeeDetail;
import com.w83ll43.hospital.model.vo.Registration;
import com.w83ll43.hospital.service.*;
import com.w83ll43.hospital.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author w83ll43
 * @description 针对表【registered_order(挂号单表)】的数据库操作Service实现
 * @createDate 2023-05-17 22:33:28
 */
@Service
public class RegisteredOrderServiceImpl extends ServiceImpl<RegisteredOrderMapper, RegisteredOrder>
        implements RegisteredOrderService {

    @Resource
    private UserService userService;

    @Resource
    private BillService billService;

    @Resource
    private DoctorService doctorService;

    @Resource
    private PatientService patientService;

    @Resource
    private DepartmentService departmentService;

    /**
     * 创建挂单号
     *
     * @param registeredOrderParam
     * @return
     */
    @Override
    public RegisteredOrder createRegistration(RegisteredOrderParam registeredOrderParam) {
        // 1、获取登录用户的 ID
        Long id = BaseContext.getCurrentId();

        // 2、获取登录用户
        User user = userService.getById(id);

        // 3、判断用户角色
        if (!"P".equals(user.getRole())) {
            throw new CustomException("非病人用户不能挂号！");
        }

        // 4、创建挂单号实体
        RegisteredOrder registeredOrder = new RegisteredOrder();
        registeredOrder.setPatientId(registeredOrderParam.getPatientId());

        // TODO 实际上需要判断医生是否属于这个科室
        registeredOrder.setDepartmentId(registeredOrderParam.getDepartmentId());
        registeredOrder.setDoctorId(registeredOrderParam.getDoctorId());

        // 5、设置挂单号的时间
        Date currentDate = DateUtils.getCurrentDate();
        registeredOrder.setDate(currentDate);

        // 6、创建缴费单
        Long billId = billService.createRegistrationBill(id, currentDate);
        registeredOrder.setBillId(billId);

        // 7、保存至数据库
        this.save(registeredOrder);

        return registeredOrder;
    }

    /**
     * 获取挂号缴费单费用明细
     * @param billId
     * @return
     */
    @Override
    public List<FeeDetail> getRegistrationFeeDetails(Long billId) {
        // 挂号缴费单的金额即挂号费用
        Bill bill = billService.getById(billId);

        List<FeeDetail> feeDetails = new ArrayList<>();

        FeeDetail feeDetail = new FeeDetail();
        feeDetail.setName("挂号");
        feeDetail.setType("挂号费用");
        feeDetail.setPrice(bill.getAmount());
        feeDetail.setDate(bill.getDate());

        feeDetails.add(feeDetail);
        return feeDetails;
    }


    public Registration getRegistrationByBillId(Long billId) {
        Registration registration = new Registration();
        LambdaQueryWrapper<RegisteredOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RegisteredOrder::getBillId, billId);

        RegisteredOrder registeredOrder = this.getOne(lambdaQueryWrapper);
        registration.setId(registeredOrder.getId());

        Doctor doctor = doctorService.getById(registeredOrder.getDoctorId());
        registration.setDoctorName(doctor.getName());

        Patient patient = patientService.getById(registeredOrder.getPatientId());
        registration.setPatientName(patient.getName());

        Department department = departmentService.getById(registeredOrder.getDepartmentId());
        registration.setDepartmentName(department.getName());

        registration.setDate(registeredOrder.getDate());

        Bill bill = billService.getById(billId);
        registration.setAmount(bill.getAmount());

        registration.setStatus(bill.getStatus());
        return registration;
    }

    @Override
    public List<Registration> getRegistrations() {
        List<Registration> registrations = new ArrayList<>();

        LambdaQueryWrapper<Bill> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // TODO 类型 1 挂号单 常量
        lambdaQueryWrapper.eq(Bill::getType, 1);

        List<Bill> bills = billService.list(lambdaQueryWrapper);
        for (Bill bill : bills) {
            Registration registration = getRegistrationByBillId(bill.getId());
            registrations.add(registration);
        }

        return registrations;

    }
}




