package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.service.RegisteredOrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Resource
    private RegisteredOrderService registeredOrderService;

    @PutMapping("/payWithRegistration/{id}")
    public Result payWithRegistration(@PathVariable("id") Long id) {
        Boolean result = registeredOrderService.payBillWithRegistration(id);
        return result ? Result.success("缴费成功！") : Result.error("缴费失败！");
    }
}