package com.yidatec.vo;

import com.yidatec.model.Customer;

/**
 * @author xf
 */
public class CustomerVO extends Customer{
    private Integer draw;
    private Integer length;
    private Integer start;
    private String creator;
    private String followTime;
    private String nextTime;
    private String followStartTime;
    private String followEndTime;
    private String nextStartTime;
    private String nextEndTime;

    public String getNextStartTime() {
        return nextStartTime;
    }

    public void setNextStartTime(String nextStartTime) {
        this.nextStartTime = nextStartTime;
    }

    public String getNextEndTime() {
        return nextEndTime;
    }

    public void setNextEndTime(String nextEndTime) {
        this.nextEndTime = nextEndTime;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }

    public String getFollowTime() {
        return followTime;
    }

    public void setFollowTime(String followTime) {
        this.followTime = followTime;
    }

    public String getFollowStartTime() {
        return followStartTime;
    }

    public void setFollowStartTime(String followStartTime) {
        this.followStartTime = followStartTime;
    }

    public String getFollowEndTime() {
        return followEndTime;
    }

    public void setFollowEndTime(String followEndTime) {
        this.followEndTime = followEndTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    private int isAll = 0;

    public int getIsAll() {
        return isAll;
    }

    public void setIsAll(int isAll) {
        this.isAll = isAll;
    }
}
