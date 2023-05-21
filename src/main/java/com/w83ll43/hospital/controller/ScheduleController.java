package com.w83ll43.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.Schedule;
import com.w83ll43.hospital.service.ScheduleService;
import com.w83ll43.hospital.utils.DateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Resource
    public ScheduleService scheduleService;

    @PostMapping
    public Result addSchedule(@RequestBody Schedule schedule) {
        // 1、判断时间段是否有交集

        // 先获取当前医生的值班条目
        LambdaQueryWrapper<Schedule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Schedule::getDoctorId, schedule.getDoctorId());
        List<Schedule> schedules = scheduleService.list(lambdaQueryWrapper);

        // 判断是否有交集
        Date startTimeOne = schedule.getStartTime();
        Date endTimeOne = schedule.getEndTime();
        for (Schedule e : schedules) {
            Date startTimeTwo = e.getStartTime();
            Date endTimeTwo = e.getEndTime();
            Boolean isInterSection = DateUtils.IsInterSection(startTimeOne, endTimeOne, startTimeTwo, endTimeTwo);
            if (isInterSection) {
                return Result.error("该医生时间有冲突！");
            }
        }

        // 2、保存到数据库
        boolean result = scheduleService.save(schedule);
        return result ? Result.success("排班成功！") : Result.error("排班失败！");
    }
}
