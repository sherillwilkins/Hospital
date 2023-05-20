package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.service.RegisteredOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Resource
    private RegisteredOrderService registeredOrderService;

}