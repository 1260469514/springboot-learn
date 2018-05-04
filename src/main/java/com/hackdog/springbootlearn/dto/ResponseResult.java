package com.hackdog.springbootlearn.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应结果
 *
 * @author 10
 */
@Data
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 6684466499111347684L;
    private String code;
    private String msg;
    private T data;
}
