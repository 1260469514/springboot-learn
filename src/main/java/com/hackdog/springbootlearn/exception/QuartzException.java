package com.hackdog.springbootlearn.exception;

import java.io.Serializable;

public class QuartzException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 5644318657323250540L;

    private String msg;

    private int code = 500;

    public QuartzException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public QuartzException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public QuartzException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public QuartzException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}