/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.QuartzMS.schedule;

import com.acosux.QuartzMS.config.AutoWiringSpringBeanJobFactory;
import java.util.Properties;
import java.util.UUID;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;



/**
 *
 * @author mario
 */

@Configuration
public class QuartzConfig {
    
    private static final Logger log = LoggerFactory.getLogger("QuartzConfig");
    private final ApplicationContext applicationContext;

    public QuartzConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    /**
     * 
     * @return jobFactory a Factory for build jobs base on AutoWiring
     */
    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
    
    /**
     * 
     * @param triggers allow array of triggers
     * @return factory for build schedulers
     */
    @Bean
    public SchedulerFactoryBean scheduler(Trigger... triggers) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        Properties properties = new Properties();
        properties.setProperty("org.quartz.scheduler.instanceName", "ADA_BILLING"); 
        properties.setProperty("org.quartz.scheduler.instanceId", String.valueOf(UUID.randomUUID()));
        schedulerFactory.setOverwriteExistingJobs(true);
        schedulerFactory.setAutoStartup(true);
        schedulerFactory.setQuartzProperties(properties);
        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
       if (ArrayUtils.isNotEmpty(triggers)) {
            schedulerFactory.setTriggers(triggers);
        }
        return schedulerFactory;
    }

    /**
     * 
     * @param jobDetail object that contain details about of job
     * @param cronExpression
     * @param triggerName
     * @return Factory that allow build a cronTrigger
     */    
     static CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression, String triggerName) {
        log.debug("createCronTrigger(jobDetail={}, cronExpression={}, triggerName={})", jobDetail.toString(), cronExpression, triggerName);
        // To fix an issue with time-based cron jobs
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setStartDelay(0L);
        factoryBean.setName(triggerName);
        return factoryBean;
    }
     /**
      * 
      * @param jobClass the class that contain a Job for execution
      * @param jobName Name of job
      * @return  Factory that allow build a jobs details
      */    
    static JobDetailFactoryBean createJobDetail(Class jobClass, String jobName) {
        log.debug("createJobDetail(jobClass={}, jobName={})", jobClass.getName(), jobName);
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setName(jobName);
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(true);
        return factoryBean;
    }
    
}
