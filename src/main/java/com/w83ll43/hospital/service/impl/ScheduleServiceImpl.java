package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.mapper.ScheduleMapper;
import com.w83ll43.hospital.model.domain.Schedule;
import com.w83ll43.hospital.service.ScheduleService;
import com.w83ll43.hospital.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author w83ll43
* @description 针对表【schedule(值班表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule>
    implements ScheduleService{

    /**
     * 获取医生当天的时间表
     * @param doctorId
     * @return
     */
    @Override
    public List<Schedule> getTodaySchedule(Long doctorId) {
        // 1、获取医生所有值班安排
        List<Schedule> schedules = getScheduleByDoctorId(doctorId);

        // 2、判断值班条目是否是今天的日期
        ArrayList<Schedule> list = new ArrayList<>();
        for (Schedule schedule : schedules) {
            if (DateUtils.isToday(schedule.getStartTime())) {
                list.add(schedule);
            }
        }

        return list;
    }

    /**
     * 获取医生的所有值班安排
     * @param doctorId
     * @return
     */
    @Override
    public List<Schedule> getScheduleByDoctorId(Long doctorId) {
        LambdaQueryWrapper<Schedule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Schedule::getDoctorId, doctorId);
        return this.list(lambdaQueryWrapper);
    }

    /**
     * 获取科室当天的值班安排
     * @param departmentId
     * @return
     */
    @Override
    public List<Schedule> getTodayScheduleByDepartmentId(Long departmentId) {
        // 1、获取科室所有值班安排

        List<Schedule> schedules = getScheduleByDepartmentId(departmentId);

        // 2、判断值班条目是否是今天的日期
        ArrayList<Schedule> list = new ArrayList<>();
        for (Schedule schedule : schedules) {
            if (DateUtils.isToday(schedule.getStartTime())) {
                list.add(schedule);
            }
        }

        return list;
    }

    /**
     * 获取科室所有的值班安排
     * @param departmentId
     * @return
     */
    @Override
    public List<Schedule> getScheduleByDepartmentId(Long departmentId) {
        LambdaQueryWrapper<Schedule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Schedule::getDepartmentId, departmentId);
        return list(lambdaQueryWrapper);
    }
}




