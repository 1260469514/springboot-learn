package com.hackdog.springbootlearn.shiro.service;

import java.util.Set;

public interface PermService {

    /**
     * 获取权限集合根据用户id
     *
     * @param {Long : uid} 用户id
     * @return 权限集合
     */
    Set<String> getPermsByUserId(Long uid);
}
