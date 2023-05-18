package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.Patient;
import com.w83ll43.hospital.model.domain.RegisteredOrder;
import com.w83ll43.hospital.model.domain.User;
import com.w83ll43.hospital.model.param.RegisteredOrderParam;
import com.w83ll43.hospital.service.RegisteredOrderService;
import com.w83ll43.hospital.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Resource
    private UserService userService;

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
        return Result.success(registeredOrder);
    }

    /**
     * 根据 ID 查询挂号单
     * @param id
     * @return
     */
    @GetMapping("/registration/{id}")
    public Result<RegisteredOrder> getRegisteredOrderById(@PathVariable("id") Long id) {
        return null;
    }

}
