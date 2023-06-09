package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.mapper.DoctorMapper;
import com.w83ll43.hospital.model.domain.Doctor;
import com.w83ll43.hospital.service.DoctorService;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【doctor(医生表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor>
    implements DoctorService{

}




