package com.w83ll43.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.hospital.mapper.UserMapper;
import com.w83ll43.hospital.model.domain.User;
import com.w83ll43.hospital.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-05-17 22:33:28
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        return this.baseMapper.selectOne(queryWrapper);
    }

}




