package com.yidatec.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.model.Customer;
import com.yidatec.util.CustomLocalDateSerializer;

import java.time.LocalDate;

/**
 * @author jrw
 * 后来追加的5个报表
 * 这是第一个
 * 显示一年跟进和新建客户
 * */
public class CustomerNewFollowVO extends Customer{
    private String followCount; // 跟进数量
    private String count; // 新建数量
    private String year; // 年
    private String ym; // 年月

    public String getFollowCount() {
        return followCount;
    }

    public void setFollowCount(String followCount) {
        this.followCount = followCount;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }
}
