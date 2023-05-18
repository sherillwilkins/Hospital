package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.common.BaseContext;
import com.w83ll43.hospital.common.CustomException;
import com.w83ll43.hospital.mapper.RegisteredOrderMapper;
import com.w83ll43.hospital.model.domain.RegisteredOrder;
import com.w83ll43.hospital.model.domain.User;
import com.w83ll43.hospital.model.param.RegisteredOrderParam;
import com.w83ll43.hospital.service.BillService;
import com.w83ll43.hospital.service.RegisteredOrderService;
import com.w83ll43.hospital.service.UserService;
import com.w83ll43.hospital.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
        registeredOrder.setPatientId(id);

        // TODO 实际上需要判断医生是否属于这个科室
        registeredOrder.setDepartmentId(registeredOrderParam.getDepartmentId());
        registeredOrder.setDoctorId(registeredOrderParam.getDoctorId());

        // 5、设置挂单号的时间
        Date currentDate = DateUtils.getCurrentDate();
        registeredOrder.setDate(currentDate);

        // 6、创建缴费单
        Long billId = billService.createRegistrationBill(id, currentDate);
        registeredOrder.setBillId(billId);

        return registeredOrder;
    }
}




