package com.hackdog.springbootlearn.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 */

@Data
public class QuartzJob implements Serializable {
    private static final long serialVersionUID = 8065606580350923482L;
    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    /**
     * 任务编号
     */
    private Long jobId;
    /*
     * spring bean名称
     */
    private String beanName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数
     */
    private String params;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 状态：0：暂停，1：正常
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 创建时间
     */
    private String gmtCreateStr;
    /**
     * 修改时间
     */
    private Date gmtModified;
}
