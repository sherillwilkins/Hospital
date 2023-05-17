package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.domain.Prescription;
import com.w83ll43.hospital.mapper.PrescriptionMapper;
import com.w83ll43.hospital.service.PrescriptionService;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【prescription(处方表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionMapper, Prescription>
    implements PrescriptionService{

}




