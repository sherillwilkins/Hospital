package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.service.PrescriptionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("prescription")
public class PrescriptionController {

    @Resource
    private PrescriptionService prescriptionService;

    @PutMapping("/payWithPrescription/{id}")
    public Result payWithPrescription(@PathVariable("id") Long id) {
        Boolean result = prescriptionService.payWithPrescription(id);
        return result ? Result.success("缴费成功！") : Result.error("缴费失败！");
    }
}
