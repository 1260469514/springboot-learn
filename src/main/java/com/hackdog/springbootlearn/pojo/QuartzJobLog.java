package com.hackdog.springbootlearn.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 定时任务日志
 *
 * @author hackdog
 * @date 2017-11-13
 */
@Data
public class QuartzJobLog implements Serializable {

    private static final long serialVersionUID = 8348569081659842361L;
    /**
     * 记录id
     */
    private Long logId;

    /**
     * 任务id
     */
    private Long jobId;

    /**
     * spring bean 名称
     */
    private String beanName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * 状态，0：失败，1：成功
     */
    private Integer status;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 耗时（ms）
     */
    private Integer times;

    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    private String gmtCreateStr;


}
