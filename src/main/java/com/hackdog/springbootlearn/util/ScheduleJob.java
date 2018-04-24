package com.hackdog.springbootlearn.util;

import com.hackdog.springbootlearn.pojo.QuartzJob;
import com.hackdog.springbootlearn.pojo.QuartzJobLog;
import com.hackdog.springbootlearn.service.IQuartzJobLogService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 定时任务调度中心
 *
 * @author hackdog
 * @Date 2017-11-15
 */
public class ScheduleJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ExecutorService service = Executors.newSingleThreadExecutor();

    protected void executeInternal(JobExecutionContext context) {
        QuartzJob scheduleJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_PARAM_KEY);
        IQuartzJobLogService quartzJobLogManager = (IQuartzJobLogService) SpringContextsUtil.getBean("quartzJobLogServiceImpl");
        //获取spring bean

        //数据库保存执行记录
        QuartzJobLog log = new QuartzJobLog();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
                    scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);

            future.get();

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int) times);
            //任务状态    0：失败    1：成功
            log.setStatus(1);
            log.setError(StringUtils.substring("任务执行成功，无错误信息！", 0, 2000));
            logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");

        } catch (Exception e) {

            logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int) times);

            //任务状态    0：失败    1：成功
            log.setStatus(0);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));//只保存2000字符异常内容

        } finally {
            quartzJobLogManager.saveQuartzJobLog(log);
        }
    }

}
