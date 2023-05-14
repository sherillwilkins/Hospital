package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.domain.Drug;
import com.w83ll43.hospital.mapper.DrugMapper;
import com.w83ll43.hospital.service.DrugService;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【drug】的数据库操作Service实现
* @createDate 2023-05-14 14:25:26
*/
@Service
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug>
    implements DrugService{

}



