package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.HospitalizationFile;
import com.w83ll43.hospital.service.HospitalizationFileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FileController {
    
    @Resource
    private HospitalizationFileService fileService;

    @PostMapping
    public Result addFile(@RequestBody HospitalizationFile file) {
        boolean result = fileService.save(file);
        return result ? Result.success("添加住院档案成功！") : Result.error("添加住院档案失败！");
    }

    @DeleteMapping("/{id}")
    public Result deleteFileById(@PathVariable("id") Long id) {
        boolean result = fileService.removeById(id);
        return result ? Result.success("删除住院档案成功！") : Result.error("删除住院档案失败！");
    }

    @PutMapping
    public Result updateFileById(@RequestBody HospitalizationFile file) {
        boolean result = fileService.updateById(file);
        return result ? Result.success("更新住院档案成功！") : Result.error("更新住院档案失败！");
    }
}
