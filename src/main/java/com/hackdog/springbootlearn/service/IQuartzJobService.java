package com.hackdog.springbootlearn.service;

import com.hackdog.springbootlearn.exception.QuartzException;
import com.hackdog.springbootlearn.pojo.QuartzJob;

import java.util.Map;

/**
 * 定时任务调度
 *
 * @author hackdog
 * @date 2017-11-13
 */
public interface IQuartzJobService {
    Map<String, Object> jobList(QuartzJob job, Integer pageNum, Integer pageSize);

    Map<String, Object> addJob(QuartzJob job) throws QuartzException;

    QuartzJob getQuartzJobById(Long jobId);

    Map<String, Object> updateQuartzJob(QuartzJob job) throws QuartzException;

    Map<String, Object> batchRemoveQuartzJob(Long[] id) throws QuartzException;

    Map<String, Object> run(Long[] id) throws QuartzException;

    Map<String, Object> pause(Long[] id) throws QuartzException;

    Map<String, Object> resume(Long[] id) throws QuartzException;
}
