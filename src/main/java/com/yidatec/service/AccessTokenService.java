package com.yidatec.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yidatec.util.ConfProperties;
import com.yidatec.util.HttpClientHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author QuShengWen
 */


public class AccessTokenService {

    protected static final Log logger = LogFactory
            .getLog(AccessTokenService.class);

    /** access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token */
    public static String access_token = "";
    public static long expires_in ;

    /** 获取access token的URL &appid=APPID&secret=APPSECRET */
    public final static String URL_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    @Autowired
    ConfProperties confProperties;

    @Autowired
    private Scheduler scheduler;

    private long repeatInterval;

    public synchronized String getAccessToken(){
        return access_token;
    }

    public synchronized long getExpiresIn(){
        return expires_in;
    }

    public synchronized void execute() {
        for (int i = 0; i < 2; i++) {
            try {
                String json = HttpClientHelper
                        .getTrustHttps(URL_TOKEN + "&appid=" + getWechatAppId() + "&secret="
                                + getWechatAppSecret());
                if (json != null) {
                    JsonNode jsonObj = new ObjectMapper().readTree(json);
                    access_token = jsonObj.findValue("access_token").asText();
                    expires_in = jsonObj.findValue("expires_in").asLong();
                    if (access_token == null || access_token.trim().isEmpty()) {
                        access_token = null;
                        expires_in = 0;
                        repeatInterval = 0;
                    }else {
                        if(repeatInterval != expires_in * 1000) {
                            repeatInterval = expires_in * 1000;
                            rescheduleJob();
                        }
                        break;
                    }
                }

            }catch(Exception ex){
                access_token = null;
                expires_in = 0;
                repeatInterval = 0;
                logger.error(ex.getMessage(),ex);
            }
        }

    }


    public long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public void rescheduleJob(){
        try {
            TriggerKey tk = new TriggerKey("accessTokenTrigger", Scheduler.DEFAULT_GROUP);
            SimpleTriggerImpl trigger = (SimpleTriggerImpl)scheduler.getTrigger(tk);
            trigger.setRepeatInterval((expires_in > 2 ? (expires_in - 2) : expires_in) * 1000);
            scheduler.rescheduleJob(tk, trigger);
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
        }
    }

    public Scheduler getScheduler(){
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    private String getWechatAppSecret(){
        return confProperties.getWeChatAppSecret();
    }

    private String getWechatAppId(){
        return confProperties.getWeChatAppId();
    }
}
