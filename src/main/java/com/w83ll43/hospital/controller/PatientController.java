package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.Bill;
import com.w83ll43.hospital.model.domain.Patient;
import com.w83ll43.hospital.model.domain.RegisteredOrder;
import com.w83ll43.hospital.model.domain.User;
import com.w83ll43.hospital.model.param.RegisteredOrderParam;
import com.w83ll43.hospital.model.vo.FeeDetail;
import com.w83ll43.hospital.service.BillService;
import com.w83ll43.hospital.service.PatientService;
import com.w83ll43.hospital.service.RegisteredOrderService;
import com.w83ll43.hospital.service.UserService;
import com.w83ll43.hospital.service.impl.PrescriptionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Resource
    private UserService userService;

    @Resource
    private PatientService patientService;

    @Resource
    private BillService billService;

    @Resource
    private PrescriptionServiceImpl prescriptionService;

    @Resource
    private RegisteredOrderService registeredOrderService;

    /**
     * 病人注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result<Patient> register(@RequestBody User user) {
        return null;
    }

    /**
     * 病人挂号
     * @param registeredOrderParam
     * @return
     */
    @PostMapping("/registration")
    public Result<RegisteredOrder> registeredOrder(@RequestBody RegisteredOrderParam registeredOrderParam) {
        RegisteredOrder registeredOrder = registeredOrderService.createRegistration(registeredOrderParam);

        if (registeredOrder == null) {
            return Result.error("未知错误！挂号失败！");
        }

        return Result.success(registeredOrder);
    }

    /**
     * 根据 ID 查询挂号单
     * @param id
     * @return
     */
    @GetMapping("/registration/{id}")
    public Result<RegisteredOrder> getRegisteredOrderById(@PathVariable("id") Long id) {
        RegisteredOrder registeredOrder = registeredOrderService.getById(id);

        if (registeredOrder == null) {
            return Result.error("挂号单不存在！");
        }

        return Result.success(registeredOrder);
    }

    /**
     * 病人缴费
     * 允许为他人缴费
     * @param id
     * @return
     */
    @PutMapping("/pay/{id}")
    public Result<String> payBill(@PathVariable("id") Long id) {
        Boolean result = billService.payBill(id);

        if (result == null) {
            return Result.error("未知错误！");
        }

        return Result.success("缴费成功！");
    }

    /**
     * 根据 ID 获取缴费单
     * @param id
     * @return
     */
    @GetMapping("/bill/{id}")
    public Result<Bill> getBillById(@PathVariable("id") Long id) {
        Bill bill = billService.getById(id);

        if (bill == null) {
            return Result.error("缴费单不存在！");
        }

        return Result.success(bill);
    }

    /**
     * 查询费用明细
     * 事实上 新增一个费用明细数据库表为佳
     * @param billId 缴费单编号
     * @return
     */
    @GetMapping("/fee/{id}")
    public Result<List<FeeDetail>> getFeeDetails(@PathVariable("id") Long billId) {
        // 1、获取缴费单类型
        Bill bill = billService.getById(billId);
        Integer type = bill.getType();

        List<FeeDetail> feeDetails = new ArrayList<>();

        // 2、处方缴费单费用明细
        if (type.equals(2)) {
            feeDetails = prescriptionService.getPrescriptionFeeDetails(billId);
        }

        // 挂号缴费单费用明细
        if (type.equals(1)) {
            feeDetails = registeredOrderService.getRegistrationFeeDetails(billId);
        }
        return Result.success(feeDetails);
    }

    /**
     * 获取所有病人信息
     * @return
     */
    @GetMapping("/list")
    public Result<List<Patient>> getPatients() {
        List<Patient> patients = patientService.list();
        return Result.success(patients);
    }

    /**
     * 根据 ID 获取病人信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Patient> getPatientById(@PathVariable("id") Long id) {
        Patient patient = patientService.getById(id);
        return Result.success(patient);
    }

}
