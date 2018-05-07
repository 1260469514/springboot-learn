package com.hackdog.springbootlearn.shiro.service.impl;

import com.hackdog.springbootlearn.pojo.User;
import com.hackdog.springbootlearn.shiro.mapper.UserMapper;
import com.hackdog.springbootlearn.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String userName, String pwd) {
        User user = new User();
        return userMapper.getUserByUserName(user);
    }
}
