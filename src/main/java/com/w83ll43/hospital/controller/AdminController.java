package com.w83ll43.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.*;
import com.w83ll43.hospital.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * 批量删除->删除成功的前提为没有其他表的外键与之关联
     * 这里假设不会出现这种情况
     */

    @Resource
    private UserService userService;

    @Resource
    private DoctorService doctorService;

    @Resource
    private SectionService sectionService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private DrugService drugService;

    /**
     * 管理员登录
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user, HttpServletRequest request) {
        // 1、校验、判断数据是否为空
        if (StringUtils.isBlank(user.getUsername())) {
            return Result.error("用户名不能为空！");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return Result.error("密码不能为空！");
        }

        // 2、根据用户名查询用户
        User userByUsername = userService.getUserByUsername("admin");

        // 3、判断权限、这里就直接判断用户名是否为 admin
        if (userByUsername == null) {
            return Result.error("权限不足！");
        }

        // 4、验证密码
        if (!user.getPassword().equals(userByUsername.getPassword())) {
            return Result.error("密码错误！");
        }

        // 5、登录成功 保存 session
        request.getSession().setAttribute("user", userByUsername.getId());

        return Result.success(userByUsername);
    }

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("/user/list")
    public Result<List<User>> getUsers() {
        List<User> users = userService.list();
        return Result.success(users);
    }

    /**
     * 添加医生
     * @param doctor
     * @return
     */
    @PostMapping("/doctor")
    public Result<Doctor> addDoctor(@RequestBody Doctor doctor) {
        long doctorId = IdWorker.getId(doctor);
        doctor.setId(doctorId);
        doctorService.save(doctor);
        return Result.success(doctor);
    }

    /**
     * 根据 ID 获取医生信息
     * @param id
     * @return
     */
    @GetMapping("/doctor/{id}")
    public Result<Doctor> getDoctorById(@PathVariable("id") Long id) {
        Doctor doctor = doctorService.getById(id);
        return Result.success(doctor);
    }

    /**
     * 获取所有医生
     * @return
     */
    @GetMapping("/doctor/list")
    public Result<List<Doctor>> getDoctors() {
        List<Doctor> doctors = doctorService.list();
        return Result.success(doctors);
    }

    /**
     * 分页查询
     * 可以通过名称查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/doctor/page")
    public Result<Page<Doctor>> doctorPage(int page, int pageSize, String name) {
        // 1、构造分页构造器
        Page<Doctor> pageInfo = new Page<>(page, pageSize);

        // 2、构造条件构造器
        LambdaQueryWrapper<Doctor> queryWrapper = new LambdaQueryWrapper<>();
        // 添加排序条件 根据 id 进行排序
        queryWrapper.orderByAsc(Doctor::getId);

        // 查询条件
        queryWrapper.like(name != null, Doctor::getName, name);

        // 3、进行分页查询
        doctorService.page(pageInfo, queryWrapper);

        return Result.success(pageInfo);
    }

    /**
     * 更新医生信息
     * @param doctor
     * @return
     */
    @PutMapping("/doctor")
    public Result<Doctor> updateDoctor(@RequestBody Doctor doctor) {
        return null;
    }

    /**
     * 根据 IDs 删除医生信息
     * @param ids
     * @return
     */
    @DeleteMapping("/doctor")
    public Result<String> deleteDoctorByIds(@RequestParam List<Long> ids) {
        boolean result = doctorService.removeByIds(ids);
        return result ? Result.success("批量删除医生成功！") : Result.error("批量删除医生失败！");
    }

    /**
     * 添加部门
     * @param section
     * @return
     */
    @PostMapping("/section")
    public Result<Section> addSection(@RequestBody Section section) {
        // 1、生成 ID
        long id = IdWorker.getId(section);
        section.setId(id);

        // 2、保存至数据库
        sectionService.save(section);

        return Result.success(section);
    }

    /**
     * 根据 ID 获取部门
     * @param id
     * @return
     */
    @GetMapping("/section/{id}")
    public Result<Section> getSectionById(@PathVariable("id") Long id) {
        Section section = sectionService.getById(id);

        if (section == null) {
            return Result.error("部门不存在！");
        }

        return Result.success(section);
    }

    /**
     * 根据 ID 修改部门信息
     * @param section
     * @return
     */
    @PutMapping("/section")
    public Result<Section> updateSectionById(@RequestBody Section section) {
        boolean result = sectionService.updateById(section);
        return result ? Result.success(section) : Result.error("更新部门失败！");
    }

    /**
     * 获取所有部门信息
     * @return
     */
    @GetMapping("/section/list")
    public Result<List<Section>> getSections() {
        List<Section> sections = sectionService.list();
        return Result.success(sections);
    }

    /**
     * 分页获取部门信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/section/page")
    public Result<Page<Section>> sectionPage(int page, int pageSize, String name) {
        // 1、构造分页构造器
        Page<Section> pageInfo = new Page<>(page, pageSize);

        // 2、构造条件构造器
        LambdaQueryWrapper<Section> lambdaQueryWrapper = new LambdaQueryWrapper<Section>();
        lambdaQueryWrapper.like(name != null, Section::getName, name);
        lambdaQueryWrapper.orderByAsc(Section::getId);

        // 3、分页查询
        sectionService.page(pageInfo, lambdaQueryWrapper);

        return Result.success(pageInfo);
    }

    /**
     * 根据 IDS 删除部门信息
     * 使用集合接收参数需要使用 @RequestParam 注解
     * @param ids
     * @return
     */
    @DeleteMapping("/section")
    public Result<String> deleteSectionByIds(@RequestParam List<Long> ids) {
        boolean result = sectionService.removeByIds(ids);
        return result ? Result.success("批量删除部门成功！") : Result.error("批量删除部门失败！");
    }

    /**
     * 添加科室
     * @param department
     * @return
     */
    @PostMapping("/department")
    public Result<Department> addDepartment(@RequestBody Department department) {
        long id = IdWorker.getId(department);
        department.setId(id);
        departmentService.save(department);
        return Result.success(department);
    }

    /**
     * 根据 ID 查询科室
     * @param id
     * @return
     */
    @GetMapping("/department/{id}")
    public Result<Department> getDepartmentById(@PathVariable("id") Long id) {
        Department department = departmentService.getById(id);

        if (department == null) {
            return Result.error("部门不存在！");
        }

        return Result.success(department);
    }

    /**
     * 根据 ID 修改科室信息
     * @param department
     * @return
     */
    @PutMapping("/department")
    public Result<Department> updateDepartmentById(@RequestBody Department department) {
        boolean result = departmentService.updateById(department);
        return result ? Result.success(department) : Result.error("修改科室信息失败");
    }

    /**
     * 获取所有科室
     * @return
     */
    @GetMapping("/department/list")
    public Result<List<Department>> getDepartments() {
        List<Department> departments = departmentService.list();
        return Result.success(departments);
    }

    /**
     * 分页查询科室
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/department/page")
    public Result<Page<Department>> departmentPage(int page, int pageSize, String name) {
        // 1、构造分页构造器
        Page<Department> pageInfo = new Page<>(page, pageSize);

        // 2、构造条件构造器
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(name != null, Department::getName, name);
        lambdaQueryWrapper.orderByAsc(Department::getId);

        // 3、分页查询
        departmentService.page(pageInfo, lambdaQueryWrapper);

        return Result.success(pageInfo);
    }

    /**
     * 根据 IDS 批量删除科室
     * @param ids
     * @return
     */
    @DeleteMapping("/department")
    public Result<String> deleteDepartmentById(@RequestParam List<Long> ids) {
        boolean result = departmentService.removeByIds(ids);
        return result ? Result.success("批量删除科室成功！") : Result.error("批量删除科室失败！");
    }

    /**
     * 添加药品
     * @param drug
     * @return
     */
    @PostMapping("/drug")
    public Result<Drug> addDrug(@RequestBody Drug drug) {
        long id = IdWorker.getId(drug);
        drug.setId(id);
        drugService.save(drug);
        return Result.success(drug);
    }

    /**
     * 根据 ID 查询药品
     * @param id
     * @return
     */
    @GetMapping("/drug/{id}")
    public Result<Drug> getDrugById(@PathVariable("id") Long id) {
        Drug drug = drugService.getById(id);

        if (drug == null) {
            return Result.error("部门不存在！");
        }

        return Result.success(drug);
    }

    /**
     * 根据 ID 修改药品信息
     * @param drug
     * @return
     */
    @PutMapping("/drug")
    public Result<Drug> updateDrugById(@RequestBody Drug drug) {
        boolean result = drugService.updateById(drug);
        return result ? Result.success(drug) : Result.error("修改药品信息失败");
    }

    /**
     * 获取所有药品
     * @return
     */
    @GetMapping("/drug/list")
    public Result<List<Drug>> getDrugs() {
        List<Drug> drugs = drugService.list();
        return Result.success(drugs);
    }

    /**
     * 分页查询药品
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/drug/page")
    public Result<Page<Drug>> drugPage(int page, int pageSize, String name) {
        // 1、构造分页构造器
        Page<Drug> pageInfo = new Page<>(page, pageSize);

        // 2、构造条件构造器
        LambdaQueryWrapper<Drug> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(name != null, Drug::getName, name);
        lambdaQueryWrapper.orderByAsc(Drug::getId);

        // 3、分页查询
        drugService.page(pageInfo, lambdaQueryWrapper);

        return Result.success(pageInfo);
    }

    /**
     * 根据 IDS 批量删除药品
     * @param ids
     * @return
     */
    @DeleteMapping("/drug")
    public Result<String> deleteDrugById(@RequestParam List<Long> ids) {
        boolean result = drugService.removeByIds(ids);
        return result ? Result.success("批量删除药品成功！") : Result.error("批量删除药品失败！");
    }
}
