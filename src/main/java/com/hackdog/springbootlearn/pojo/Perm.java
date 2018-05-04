package com.hackdog.springbootlearn.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 10
 */
@Data
public class Perm implements Serializable {
    private static final long serialVersionUID = -3246249474066514606L;

    private Long pid;       // 权限id
    private String pname;   // 权限名称
    private Integer ptype;  // 权限类型：1.菜单；2.按钮
    private String pval;    // 权限值，shiro的权限控制表达式
    private Date created;   // 创建时间
    private Date updated;   // 修改时间

}
