package com.hackdog.springbootlearn.enums;

import lombok.Getter;

/**
 * 响应信息
 *
 * @author 10
 * @version 1.0.0
 */

@Getter
public enum ResponseInfo {

    UNKNOWN_ERROR("100", "未知错误"),
    FAIL("0", "fail"),
    SUCCESS("1", "success");
    private String code;
    private String msg;

    ResponseInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
