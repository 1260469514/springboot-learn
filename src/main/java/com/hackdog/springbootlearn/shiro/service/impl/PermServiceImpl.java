package com.hackdog.springbootlearn.shiro.service.impl;

import com.hackdog.springbootlearn.shiro.mapper.PermMapper;
import com.hackdog.springbootlearn.shiro.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermServiceImpl implements PermService {

    @Autowired
    private PermMapper permMapper;

    @Override
    public Set<String> getPermsByUserId(Long uid) {
        return permMapper.getPermsByUid(uid);
    }
}
