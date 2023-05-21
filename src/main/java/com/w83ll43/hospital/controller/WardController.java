package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.Ward;
import com.w83ll43.hospital.service.WardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ward")
public class WardController {

    @Resource
    private WardService wardService;

    @PostMapping
    public Result addWard(@RequestBody Ward ward) {
        boolean result = wardService.save(ward);
        return result ? Result.success("添加病房成功！") : Result.error("添加病房失败！");
    }

    @DeleteMapping("/{id}")
    public Result deleteWardById(@PathVariable("id") Long id) {
        boolean result = wardService.removeById(id);
        return result ? Result.success("删除病房成功！") : Result.error("删除病房失败！");
    }

    @PutMapping
    public Result updateWardById(@RequestBody Ward ward) {
        boolean result = wardService.updateById(ward);
        return result ? Result.success("更新病房成功！") : Result.error("更新病房失败！");
    }
}
