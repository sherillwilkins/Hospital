package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.domain.Patient;
import com.w83ll43.hospital.mapper.PatientMapper;
import com.w83ll43.hospital.service.PatientService;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【patient】的数据库操作Service实现
* @createDate 2023-05-14 14:25:26
*/
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient>
    implements PatientService{

}




