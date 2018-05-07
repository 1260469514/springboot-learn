package com.hackdog.springbootlearn.shiro.mapper;

import java.util.Set;

public interface PermMapper {

    Set<String> getPermsByUid(Long uid);
}
