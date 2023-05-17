package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.domain.Bill;
import com.w83ll43.hospital.mapper.BillMapper;
import com.w83ll43.hospital.service.BillService;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【bill(缴费单表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill>
    implements BillService{

}




