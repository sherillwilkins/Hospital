package com.w83ll43.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.hospital.model.domain.RegisteredOrder;
import com.w83ll43.hospital.model.param.RegisteredOrderParam;
import com.w83ll43.hospital.model.vo.FeeDetail;
import com.w83ll43.hospital.model.vo.Registration;

import java.util.List;

/**
* @author w83ll43
* @description 针对表【registered_order(挂号单表)】的数据库操作Service
* @createDate 2023-05-17 22:33:28
*/
public interface RegisteredOrderService extends IService<RegisteredOrder> {


    /**
     * 创建挂单号
     * @param registeredOrderParam
     * @return
     */
    RegisteredOrder createRegistration(RegisteredOrderParam registeredOrderParam);

    /**
     * 获取挂号缴费单费用明细
     * @param billId
     * @return
     */
    List<FeeDetail> getRegistrationFeeDetails(Long billId);

    List<Registration> getRegistrations();
}
