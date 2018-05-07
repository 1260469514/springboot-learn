package com.hackdog.springbootlearn.shiro.service;

import java.util.Set;

public interface RoleService {

    /**
     * 获取角色集合根据用户id
     *
     * @param {Long : uid} 用户id
     * @return 角色集合
     */
    Set<String> getRolesByUserId(Long uid);
}
