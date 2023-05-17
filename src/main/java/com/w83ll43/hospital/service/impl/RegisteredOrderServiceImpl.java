package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.domain.RegisteredOrder;
import com.w83ll43.hospital.mapper.RegisteredOrderMapper;
import com.w83ll43.hospital.service.RegisteredOrderService;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【registered_order(挂号单表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class RegisteredOrderServiceImpl extends ServiceImpl<RegisteredOrderMapper, RegisteredOrder>
    implements RegisteredOrderService{

}




