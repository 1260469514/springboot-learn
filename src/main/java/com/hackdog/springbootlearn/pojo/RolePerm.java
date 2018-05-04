package com.hackdog.springbootlearn.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolePerm implements Serializable {
    private Long roleId;
    private Long permId;//权限id
}
