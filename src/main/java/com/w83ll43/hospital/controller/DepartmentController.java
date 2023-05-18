package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.Department;
import com.w83ll43.hospital.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    /**
     * 添加科室
     * @param department
     * @return
     */
    @PostMapping
    public Result<Department> addDepartment(@RequestBody Department department) {
        return null;
    }

    /**
     * 根据 ID 删除科室
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteDepartmentById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 根据 ID 修改科室
     * @param department
     * @return
     */
    @PutMapping
    public Result<Department> updateDepartmentById(@RequestBody Department department) {
        return null;
    }

    /**
     * 根据 ID 查询科室
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Department> getDepartmentById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 获取所有科室
     * @return
     */
    @GetMapping("/all")
    public Result<List<Department>> getDepartments() {
        return null;
    }

}
