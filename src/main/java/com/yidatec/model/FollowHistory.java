package com.yidatec.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2017/11/20.
 */
public class FollowHistory extends BaseModel{
    @NotBlank(message = "必须输入跟进描述", groups = { })
    private String followDetail;
    @NotBlank(message = "必须输入跟进时间", groups = { })
    private String followTime;
    @NotBlank(message = "必须输入下次跟进时间", groups = { })
    private String nextTime;


    public String getFollowDetail() {
        return followDetail;
    }

    public void setFollowDetail(String followDetail) {
        this.followDetail = followDetail;
    }

    public String getFollowTime() {
        return followTime;
    }

    public void setFollowTime(String followTime) {
        this.followTime = followTime;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }
}
