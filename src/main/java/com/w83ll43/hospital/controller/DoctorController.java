package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    @GetMapping("/test")
    public Result<String> test() {
        return Result.success("访问成功！");
    }
}
