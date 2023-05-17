package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.domain.Department;
import com.w83ll43.hospital.mapper.DepartmentMapper;
import com.w83ll43.hospital.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【department(部门表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

}




