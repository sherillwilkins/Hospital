package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.Bed;
import com.w83ll43.hospital.service.BedService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bed")
public class BedController {

    @Resource
    private BedService bedService;

    @PostMapping
    public Result addBed(@RequestBody Bed bed) {
        boolean result = bedService.save(bed);
        return result ? Result.success("添加病床成功！") : Result.error("添加病床失败！");
    }

    @DeleteMapping("/{id}")
    public Result deleteBedById(@PathVariable("id") Long id) {
        boolean result = bedService.removeById(id);
        return result ? Result.success("删除病床成功！") : Result.error("删除病床失败！");
    }

    @PutMapping
    public Result updateBedById(@RequestBody Bed bed) {
        boolean result = bedService.updateById(bed);
        return result ? Result.success("更新病床成功！") : Result.error("更新病床失败！");
    }
}
