package com.yidatec.security;

import com.yidatec.service.AccessTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @author QuShengWen
 */
@Configuration
public class AccessTokenQuartzConfig {

    @Bean
    public AccessTokenService accessTokenService(){
        AccessTokenService ins = new AccessTokenService();
        ins.setRepeatInterval(7200000);
        return ins;

    }

    @Bean
    public MethodInvokingJobDetailFactoryBean accessTokenJobDetail(){
        MethodInvokingJobDetailFactoryBean ins = new MethodInvokingJobDetailFactoryBean();
        ins.setTargetObject(accessTokenService());
        ins.setTargetMethod("execute");
        ins.setConcurrent(false);
        return ins;
    }


    @Bean
    public SimpleTriggerFactoryBean accessTokenTrigger(){
        SimpleTriggerFactoryBean ins = new SimpleTriggerFactoryBean();
        ins.setJobDetail(accessTokenJobDetail().getObject());
        ins.setStartDelay(0);
        ins.setRepeatInterval(7000000);
        return ins;
    }


    @Bean
    public SchedulerFactoryBean schedulerFactory(){
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean ();
        schedulerFactory.setJobDetails(accessTokenJobDetail().getObject());
        schedulerFactory.setTriggers(accessTokenTrigger().getObject());
        return schedulerFactory;
    }
}
