package com.hackdog.springbootlearn.util;

import com.hackdog.springbootlearn.dto.ResponseResult;
import com.hackdog.springbootlearn.enums.ResponseInfo;

/**
 * 响应结果工具类
 *
 * @author 10
 * @version 1.0.0
 */
public class ResponseResultUtil {
    /**
     * 响应失败
     *
     * @return ResponseResult
     * @author wangzeying
     */
    public static ResponseResult fail() {
        ResponseResult responseResult
                = new ResponseResult();
        responseResult.setCode(ResponseInfo.FAIL.getCode());
        responseResult.setMsg(ResponseInfo.FAIL.getMsg());
        return responseResult;
    }

    /**
     * 响应成功
     *
     * @return responseResult
     * @author wangzeying
     */
    public static ResponseResult success() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResponseInfo.SUCCESS.getCode());
        responseResult.setMsg(ResponseInfo.SUCCESS.getMsg());
        return responseResult;
    }

    /**
     * 响应成功,需要传递响应对象
     *
     * @param data 响应对象
     * @param <T>  约束响应对象类型
     * @return responseResult
     */
    public static <T> ResponseResult success(T data) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResponseInfo.SUCCESS.getCode());
        responseResult.setMsg(ResponseInfo.SUCCESS.getMsg());
        responseResult.setData(data);
        return responseResult;
    }
}
