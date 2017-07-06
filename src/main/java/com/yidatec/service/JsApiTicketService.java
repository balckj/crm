package com.yidatec.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yidatec.util.HttpClientHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author QuShengWen
 */
@Service("jsApiTicketService")
public class JsApiTicketService {

    public static final String URL_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi";

    @Autowired
    private AccessTokenService accessTokenService;

    protected static final Log logger = LogFactory
            .getLog(JsApiTicketService.class);

    private String ticket;
    private long expires_in;

    private long repeatInterval;

    public synchronized String getJsapiTicket(){
        return ticket;
    }

    public synchronized long getExpiresIn(){
        return expires_in;
    }

    public synchronized void execute() {
        for(int i=0;i<2;i++) {
            String accessToken = accessTokenService.getAccessToken();
            if (accessToken == null) {
                accessTokenService.execute();
                accessToken = accessTokenService.getAccessToken();
            }
            String ticketUrl = URL_TICKET + "&access_token=" + accessToken;

            try {
                String json =  HttpClientHelper
                        .getTrustHttps(ticketUrl);
//                String json = null;
                if (json != null) {
                    JsonNode jsonObj = new ObjectMapper().readTree(json);
                    try {
                        ticket = jsonObj.findValue("ticket").asText();
                        expires_in = jsonObj.findValue("expires_in").asLong();
                        if (ticket == null || ticket.trim().isEmpty()) {
                            ticket = null;
                            expires_in = 0;
                        } else {
                            if(repeatInterval != expires_in * 1000) {
                                repeatInterval = expires_in * 1000;
                                rescheduleJob();
                            }
                            break;

                        }
                    }catch(Exception ee){
                        ticket = null;
                        expires_in = 0;
                        accessTokenService.execute();
                        logger.error(json, ee);
                    }
                }
            } catch (Exception ex) {
                ticket = null;
                expires_in = 0;
                logger.error(ex.getMessage(), ex);
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
            TriggerKey tk = new TriggerKey("jsTicketTrigger", Scheduler.DEFAULT_GROUP);
            SimpleTriggerImpl trigger = (SimpleTriggerImpl)accessTokenService.getScheduler().getTrigger(tk);
            trigger.setRepeatInterval((expires_in > 2 ? (expires_in - 2) : expires_in) * 1000);
            accessTokenService.getScheduler().rescheduleJob(tk, trigger);
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
        }
    }
}
