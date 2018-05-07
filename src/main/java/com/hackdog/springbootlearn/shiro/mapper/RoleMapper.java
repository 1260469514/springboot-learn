package com.hackdog.springbootlearn.shiro.mapper;

import java.util.Set;

public interface RoleMapper {

    Set<String> getRolesByUid(Long uid);
}
