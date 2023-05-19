package com.w83ll43.hospital.patient;


import com.w83ll43.hospital.common.BaseContext;
import com.w83ll43.hospital.model.domain.RegisteredOrder;
import com.w83ll43.hospital.model.param.RegisteredOrderParam;
import com.w83ll43.hospital.service.RegisteredOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PatientTest {

    @Resource
    private RegisteredOrderService registeredOrderService;

    /**
     * 测试病人挂号
     */
    @Test
    void testRegisteredOrder() {
        BaseContext.setCurrentId(1L);
        RegisteredOrderParam registeredOrderParam = new RegisteredOrderParam();
        registeredOrderParam.setPatientId(1L);
        registeredOrderParam.setDoctorId(1659069936446148610L);
        registeredOrderParam.setDepartmentId(1L);
        RegisteredOrder registeredOrder = registeredOrderService.createRegistration(registeredOrderParam);
        System.out.println("registeredOrder = " + registeredOrder);
    }

}
