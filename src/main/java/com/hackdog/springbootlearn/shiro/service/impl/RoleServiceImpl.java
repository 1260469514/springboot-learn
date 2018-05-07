package com.hackdog.springbootlearn.shiro.service.impl;

import com.hackdog.springbootlearn.shiro.mapper.RoleMapper;
import com.hackdog.springbootlearn.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> getRolesByUserId(Long uid) {
        return roleMapper.getRolesByUid(uid);
    }
}
