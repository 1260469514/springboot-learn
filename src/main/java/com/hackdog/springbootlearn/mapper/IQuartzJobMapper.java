package com.hackdog.springbootlearn.mapper;

import com.hackdog.springbootlearn.pojo.QuartzJob;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface IQuartzJobMapper {

    /**
     * 获取任务列表
     *
     * @param job 定时任务
     * @return
     */
    List<QuartzJob> jobList(@Param("job") QuartzJob job);

    /**
     * 获取任务列表总记录数
     *
     * @param job 定时任务
     * @return
     */
    Long getJobsCount(@Param("job") QuartzJob job);

    /**
     * 添加任务
     *
     * @param job 定时任务
     * @return
     */
    int addJob(@Param("job") QuartzJob job);

    /**
     * 依据任务id获取任务信息
     *
     * @param jobId 定时任务Id
     * @return
     */
    QuartzJob getJobById(Long jobId);

    /**
     * 更新任务
     *
     * @param job 定时任务
     * @return
     */
    int updateJob(QuartzJob job);

    /**
     * 批量更新任务信息状态
     *
     * @param jobIds 定时任务Ids
     * @param status 0：暂停，1：正常
     * @return
     */
    int batchUpdateJobStatusByIds(@Param("jobId") Long[] jobIds, @Param("status") int status);

    /**
     * 批量删除任务
     *
     * @param jobIds 定时任务Ids
     * @return
     */
    int batchRemoveJobByIds(Long[] jobIds);

    /**
     * 依据任务id运行任务
     * @param id
     * @return
     *//*
    Map<String, Object> run(Long[] id);

    *//**
     * 依据任务id暂停任务
     * @param id
     * @return
     *//*
    Map<String, Object> pause(Long[] id);

    *//**
     * 依据任务id回复任务执行
     * @param id
     * @return
     *//*
    Map<String, Object> resume(Long[] id);*/

    /**
     * 依据条件获取任务列表
     *
     * @param params
     * @return
     */
    List<QuartzJob> getJobListByParams(Map<String, Object> params);
}
