package com.hackdog.springbootlearn.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole implements Serializable {
    private Long roleId;
    private Long userId;
}
