package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.HospitalizationRecord;
import com.w83ll43.hospital.service.HospitalizationRecordService;
import com.w83ll43.hospital.utils.DateUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/record")
public class RecordController {
    
    @Resource
    private HospitalizationRecordService recordService;

    @PostMapping
    public Result addRecord(@RequestBody HospitalizationRecord record) {
        record.setRecordTime(DateUtils.getCurrentDate());
        boolean result = recordService.save(record);
        return result ? Result.success("添加住院记录成功！") : Result.error("添加住院记录失败！");
    }

    @DeleteMapping("/{id}")
    public Result deleteRecordById(@PathVariable("id") Long id) {
        boolean result = recordService.removeById(id);
        return result ? Result.success("删除住院记录成功！") : Result.error("删除住院记录失败！");
    }

    @PutMapping
    public Result updateRecordById(@RequestBody HospitalizationRecord record) {
        boolean result = recordService.updateById(record);
        return result ? Result.success("更新住院记录成功！") : Result.error("更新住院记录失败！");
    }
}
