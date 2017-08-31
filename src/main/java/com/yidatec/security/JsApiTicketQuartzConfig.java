package com.yidatec.security;

import com.yidatec.service.AccessTokenService;
import com.yidatec.service.JsApiTicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @author QuShengWen
 */
@Configuration
public class JsApiTicketQuartzConfig {

    @Bean
    public JsApiTicketService jsApiTicketService(){
        JsApiTicketService ins = new JsApiTicketService();
        ins.setRepeatInterval(7200000);
        return ins;

    }

    @Bean
    public MethodInvokingJobDetailFactoryBean jsApiTicketJobDetail(){
        MethodInvokingJobDetailFactoryBean ins = new MethodInvokingJobDetailFactoryBean();
        ins.setTargetObject(jsApiTicketService());
        ins.setTargetMethod("execute");
        ins.setConcurrent(false);
        return ins;
    }


    @Bean
    public SimpleTriggerFactoryBean jsApiTicketTrigger(){
        SimpleTriggerFactoryBean ins = new SimpleTriggerFactoryBean();
        ins.setJobDetail(jsApiTicketJobDetail().getObject());
        ins.setStartDelay(10000);
        ins.setRepeatInterval(7000000);
        return ins;
    }


    @Bean
    public SchedulerFactoryBean schedulerFactory(){
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean ();
        schedulerFactory.setJobDetails(jsApiTicketJobDetail().getObject());
        schedulerFactory.setTriggers(jsApiTicketTrigger().getObject());
        return schedulerFactory;
    }
}
