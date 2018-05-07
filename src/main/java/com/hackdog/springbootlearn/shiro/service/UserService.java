package com.hackdog.springbootlearn.shiro.service;

import com.hackdog.springbootlearn.pojo.User;

public interface UserService {

    /**
     * 查找用户根据名称
     *
     * @param {String : userName} 用户名称
     * @return User 用户对象
     */
    User findUserByName(String userName, String pwd);
}
