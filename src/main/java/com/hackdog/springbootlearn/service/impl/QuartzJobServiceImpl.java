package com.hackdog.springbootlearn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hackdog.springbootlearn.exception.QuartzException;
import com.hackdog.springbootlearn.mapper.IQuartzJobMapper;
import com.hackdog.springbootlearn.pojo.QuartzJob;
import com.hackdog.springbootlearn.service.IQuartzJobService;
import com.hackdog.springbootlearn.util.AppUtil;
import com.hackdog.springbootlearn.util.ScheduleStatus;
import com.hackdog.springbootlearn.util.ScheduleUtils;
import org.quartz.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import com.sinata.vo.workFlow.WsProcessWorkFlowVo;*/

/**
 * 定时任务调度
 *
 * @author hackdog
 * @date 2017-11-13
 */
@Service
public class QuartzJobServiceImpl implements IQuartzJobService {
    @Resource
    private IQuartzJobMapper iQuartzJobMapper;


    /**
     * 项目启动，初始化任务
     *
     * @throws QuartzException
     */
    @PostConstruct
    public void init() throws QuartzException {
        //获得所有状态为“启动”的定时任务
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 1);
        List<QuartzJob> jobList = iQuartzJobMapper.getJobListByParams(map);
        for (QuartzJob job : jobList) {
//			根据任务id获取触发器
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(job.getJobId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(job);
            } else {
                ScheduleUtils.updateScheduleJob(job);
            }
        }
    }

    @Override
    public Map<String, Object> jobList(QuartzJob job, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<QuartzJob> page = new PageInfo<QuartzJob>();
        Long count = iQuartzJobMapper.getJobsCount(job);
        List<QuartzJob> list = iQuartzJobMapper.jobList(job);
        Map<String, Object> map = new HashMap<String, Object>();
        if (list.size() <= 0) {
            map.put("msg", "未找到匹配的数据");
        } else {
            map.put("msg", "数据获取成功");
            map.put("code", "0");
            map.put("data", list);
            map.put("page", page);
        }
        return map;
    }

    @Override
    public Map<String, Object> addJob(QuartzJob job) throws QuartzException {
        Map<String, Object> map = new HashMap<String, Object>();
        int count = iQuartzJobMapper.addJob(job);
        map.put("count", count);
        if (count > 0) {
            //job.setStatus(ScheduleStatus.NORMAL.getValue());//创建时默认为启动
            ScheduleUtils.createScheduleJob(job);
            map.put("flag", "SUCCESS");
            map.put("msg", "新增成功！");
        } else {
            map.put("flag", "FAILED");
            map.put("msg", "新增失败！");
        }
        return AppUtil.getMap(map);
    }


    @Override
    public QuartzJob getQuartzJobById(Long jobId) {
        QuartzJob job = iQuartzJobMapper.getJobById(jobId);
        return job;
    }

    @Override
    public Map<String, Object> updateQuartzJob(QuartzJob job) throws QuartzException {
        Map<String, Object> map = new HashMap<String, Object>();
        int count = iQuartzJobMapper.updateJob(job);
        map.put("count", count);
        if (count > 0) {
            map.put("flag", "SUCCESS");
            map.put("msg", "修改成功！");
            ScheduleUtils.updateScheduleJob(job);
        } else {
            map.put("flag", "FAILED");
            map.put("msg", "修改失败！");
        }
        return AppUtil.getMap(map);
    }

    @Override
    public Map<String, Object> batchRemoveQuartzJob(Long[] id) throws QuartzException {
        Map<String, Object> map = new HashMap<String, Object>();
        int count = iQuartzJobMapper.batchRemoveJobByIds(id);
        map.put("count", count);
        if (count > 0) {
            for (Long jobId : id) {
                ScheduleUtils.deleteScheduleJob(jobId);
            }
            map.put("flag", "SUCCESS");
            map.put("msg", "删除成功！");
        } else {
            map.put("flag", "FAILED");
            map.put("msg", "删除失败！");
        }
        return AppUtil.getMap(map);
    }

    @Override
    public Map<String, Object> run(Long[] id) throws QuartzException {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Long jobId : id) {
            ScheduleUtils.run(iQuartzJobMapper.getJobById(jobId));
        }
        if (id.length > 0) {
            map.put("flag", "SUCCESS");
            map.put("msg", "启动成功！");
        } else {
            map.put("flag", "FAILED");
            map.put("msg", "启动失败,任务ID为空！");
        }
        return AppUtil.getMap(map);
    }

    @Override
    public Map<String, Object> pause(Long[] id) throws QuartzException {
        Map<String, Object> map = new HashMap<String, Object>();
        int count = iQuartzJobMapper.batchUpdateJobStatusByIds(id, ScheduleStatus.PAUSE.getValue());
        map.put("count", count);
        if (count > 0) {
            for (Long jobId : id) {
                ScheduleUtils.pauseJob(jobId);
            }
            map.put("flag", "SUCCESS");
            map.put("msg", "暂停成功！");
        } else {
            map.put("flag", "FAILED");
            map.put("msg", "暂停失败！");
        }
        return AppUtil.getMap(map);
    }

    @Override
    public Map<String, Object> resume(Long[] id) throws QuartzException {
        Map<String, Object> map = new HashMap<String, Object>();
        int count = iQuartzJobMapper.batchUpdateJobStatusByIds(id, ScheduleStatus.NORMAL.getValue());
        map.put("count", count);
        if (count > 0) {
            for (Long jobId : id) {
                ScheduleUtils.resumeJob(jobId);
            }
            map.put("flag", "SUCCESS");
            map.put("msg", "恢复成功！");
        } else {
            map.put("flag", "FAILED");
            map.put("msg", "恢复失败！");
        }
        return AppUtil.getMap(map);
    }

}
