package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.Prescription;
import com.w83ll43.hospital.model.param.PrescriptionParam;
import com.w83ll43.hospital.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    /**
     * 开具处方
     * @return
     */
    @PostMapping("/prescription")
    public Result<Prescription> writePrescription(@RequestBody PrescriptionParam prescriptionParam) {
        return null;
    }

}
