package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.mapper.SectionMapper;
import com.w83ll43.hospital.model.domain.Section;
import com.w83ll43.hospital.service.SectionService;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【section(部门表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class SectionServiceImpl extends ServiceImpl<SectionMapper, Section>
    implements SectionService{

}




