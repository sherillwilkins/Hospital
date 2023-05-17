package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.domain.Schedule;
import com.w83ll43.hospital.mapper.ScheduleMapper;
import com.w83ll43.hospital.service.ScheduleService;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【schedule(值班表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule>
    implements ScheduleService{

}




