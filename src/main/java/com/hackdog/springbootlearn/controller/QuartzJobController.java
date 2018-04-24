package com.hackdog.springbootlearn.controller;

import com.hackdog.springbootlearn.exception.QuartzException;
import com.hackdog.springbootlearn.pojo.QuartzJob;
import com.hackdog.springbootlearn.service.IQuartzJobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 定时任务
 *
 * @author hackdog
 * @date 2017-11-13
 */
@RestController
@RequestMapping("/quartz/job")
public class QuartzJobController {


    @Resource
    private IQuartzJobService quartzJobService;

    /**
     * 列表
     *
     * @param job
     * @param limit
     * @return
     */
    @RequestMapping("/pageList")
//	@LogPermission(code="QuartzJob Management",module="定时任务调度平台",optType="定时任务分页查询-定时任务")
    public Map<String, Object> pageList(QuartzJob job, Integer page, Integer limit) {
        return quartzJobService.jobList(job, page, limit);
    }

    /**
     * 新增任务
     *
     * @param job
     * @return
     * @throws QuartzException
     */
    @RequestMapping("/save")
//	@LogPermission(code="QuartzJob Management",module="定时任务调度平台",optType="新增-定时任务")
    public Map<String, Object> save(QuartzJob job) throws QuartzException {
        return quartzJobService.addJob(job);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/find")
    public QuartzJob findById(Long id) {
        return quartzJobService.getQuartzJobById(id);
    }

    /**
     * 修改任务
     *
     * @param job
     * @return
     * @throws QuartzException
     */
    @RequestMapping("/update")
//	@LogPermission(code="QuartzJob Management",module="定时任务调度平台",optType="修改-定时任务")
    public Map<String, Object> update(QuartzJob job) throws QuartzException {
        return quartzJobService.updateQuartzJob(job);
    }

    /**
     * 删除定时任务
     *
     * @param id
     * @return
     * @throws QuartzException
     */
    @RequestMapping("/remove")
//	@LogPermission(code="QuartzJob Management",module="定时任务调度平台",optType="删除-定时任务")
    public Map<String, Object> remove(Long[] id) throws QuartzException {
        return quartzJobService.batchRemoveQuartzJob(id);
    }

    /**
     * 立即运行
     *
     * @param id
     * @return
     * @throws QuartzException
     */
    @RequestMapping("/run")
//	@LogPermission(code="QuartzJob Management",module="定时任务调度平台",optType="启动-定时任务")
    public Map<String, Object> run(Long[] id) throws QuartzException {
        return quartzJobService.run(id);
    }

    /**
     * 暂停任务
     *
     * @param id
     * @return
     * @throws QuartzException
     */
    @RequestMapping("/pause")
//	@LogPermission(code="QuartzJob Management",module="定时任务调度平台",optType="暂停-定时任务")
    public Map<String, Object> pause(Long[] id) throws QuartzException {
        return quartzJobService.pause(id);
    }

    /**
     * 启用任务
     *
     * @param id
     * @return
     * @throws QuartzException
     */
    @RequestMapping("/enable")
//	@LogPermission(code="QuartzJob Management",module="定时任务调度平台",optType="启用-定时任务")
    public Map<String, Object> resume(Long[] id) throws QuartzException {
        return quartzJobService.resume(id);
    }

}
