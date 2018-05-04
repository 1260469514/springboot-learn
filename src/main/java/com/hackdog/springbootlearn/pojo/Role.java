package com.hackdog.springbootlearn.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -8806661166101301479L;

    private Long rid;       // 角色id
    private String rname;   // 角色名，用于显示
    private String rdesc;   // 角色描述
    private String rval;    // 角色值，用于权限判断
    private Date created;   // 创建时间
    private Date updated;   // 修改时间
}
