package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.domain.Ward;
import com.w83ll43.hospital.mapper.WardMapper;
import com.w83ll43.hospital.service.WardService;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【ward(病房表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class WardServiceImpl extends ServiceImpl<WardMapper, Ward>
    implements WardService{

}




