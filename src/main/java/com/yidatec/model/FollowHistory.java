package com.yidatec.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2017/11/20.
 */
public class FollowHistory extends BaseModel{
    @NotBlank(message = "必须输入跟进描述", groups = { })
    private String followDetail;
    @NotBlank(message = "必须输入付款时间", groups = { })
    private String followTime;


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
}
