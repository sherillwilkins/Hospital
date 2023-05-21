package com.w83ll43.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.hospital.model.domain.Schedule;

import java.util.List;

/**
* @author w83ll43
* @description 针对表【schedule(值班表)】的数据库操作Service
* @createDate 2023-05-17 22:33:28
*/
public interface ScheduleService extends IService<Schedule> {

    /**
     * 获取医生当天的时间表
     * @param doctorId
     * @return
     */
    List<Schedule> getTodaySchedule(Long doctorId);

    /**
     * 获取医生的所有值班安排
     * @param doctorId
     * @return
     */
    List<Schedule> getScheduleByDoctorId(Long doctorId);

    /**
     * 获取科室当天值班安排
     * @param departmentId
     * @return
     */
    List<Schedule> getTodayScheduleByDepartmentId(Long departmentId);

    /**
     * 获取科室所有值班安排
     * @param departmentId
     * @return
     */
    List<Schedule> getScheduleByDepartmentId(Long departmentId);
}
