package com.yidatec.model;

/**
 * Created by jrw on 2017/8/3.
 * 观众和活动关系表
 */
public class AudienceCampaign extends BaseModel {

    private String audienceId;
    private String campaignId;

    public String getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(String audienceId) {
        this.audienceId = audienceId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }
}
