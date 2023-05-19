package com.w83ll43.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.Doctor;
import com.w83ll43.hospital.model.domain.Drug;
import com.w83ll43.hospital.model.domain.User;
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
     * 添加医生
     * @param doctor
     * @return
     */
    @PostMapping
    public Result<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return null;
    }

    /**
     * 根据 ID 获取医生信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Doctor> getDoctorById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 获取所有医生
     * @return
     */
    @GetMapping("/all")
    public Result<List<Doctor>> getDoctors() {
        return null;
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
    @PutMapping
    public Result<Doctor> updateDoctor(@RequestBody Doctor doctor) {
        return null;
    }

    /**
     * 根据 ID 删除医生信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteDoctorById(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 新增医生
     * @param doctor
     * @param request
     * @return
     */
    @PostMapping("/add/doctor")
    public Result<String> addDoctor(@RequestBody Doctor doctor, HttpServletRequest request) {
//        // 1、校验是否登录
//        if (request.getSession().getAttribute("username") == null) {
//            return Result.error("请先登录！");
//        }
//
//        // 2、判断用户是否拥有权限
//        if (request.getSession().getAttribute("role") != "admin") {
//            return Result.error("用户无权限！");
//        }

        // 3、校验数据 （这部分前端也可以进行校验）
        if (StringUtils.isBlank(doctor.getName())) {
            return Result.error("医生姓名不能为空！");
        }

        doctorService.save(doctor);

        return Result.success("新增医生成功！");
    }

    /**
     * 新增药品
     * @param drug
     * @param request
     * @return
     */
    @PostMapping("/add/drug")
    public Result<String> addDrug(@RequestBody Drug drug, HttpServletRequest request) {
//        // 1、校验是否登录
//        if (request.getSession().getAttribute("username") == null) {
//            return Result.error("请先登录！");
//        }

        // 2、校验用户角色、只有管理员和药房管理人员才拥有权限
//        String role = (String) request.getSession().getAttribute("role");
//        if (!("admin".equals(role) || "drugManager".equals(role))) {
//            return Result.error("用户无权限！");
//        }

        // 3、校验数据合法性
        if (StringUtils.isAnyBlank(drug.getName(), drug.getSpecification())) {
            if (drug.getPrice() == null || drug.getStack() == null) {
                return Result.error("药品信息不能为空！");
            }
        }

        drugService.save(drug);

        return Result.success("新增药品成功！");
    }

    /**
     * 根据 ID 查询药品信息
     * @param id
     * @return
     */
    @GetMapping("/get/drug/{id}")
    public Result<Drug> getDrug(@PathVariable("id") Long id) {
        // 1、校验 （是否登录省略、后面使用过滤器实现）
        if (id == null) {
            return Result.error("id 不能为空！");
        }

        // 2、查询数据库
        Drug drug = drugService.getById(id);

        // 3、校验返回结果
        if (drug == null) {
            return Result.error("无法查询 id 为 " + id + " 的药品！");
        }

        return Result.success(drug);
    }

    /**
     * 修改药品信息
     * @param drug
     * @param request
     * @return
     */
    @PutMapping("/update/drug")
    public Result<String> updateDrug(@RequestBody Drug drug, HttpServletRequest request) {
        // 1、校验
        if (drug.getId() == null) {
            return Result.error("需要修改的药品 ID 不能为空！");
        }

        // 2、更新
        boolean result = drugService.updateById(drug);

        return result ? Result.success("修改成功！") : Result.error("未知错误！");
    }

    /**
     * 根据 ID 删除药品
     * @param id
     * @return
     */
    @DeleteMapping("/delete/drug/{id}")
    public Result<String> deleteDrug(@PathVariable("id") Long id) {

        boolean result = drugService.removeById(id);

        return result ? Result.success("删除成功！") : Result.error("删除失败！");
    }
}
