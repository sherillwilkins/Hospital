package com.w83ll43.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.hospital.model.domain.User;

/**
* @author w83ll43
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-05-17 22:33:28
*/
public interface UserService extends IService<User> {

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

}
