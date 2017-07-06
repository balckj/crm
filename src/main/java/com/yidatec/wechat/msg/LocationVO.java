package com.yidatec.wechat.msg;



import java.util.Date;

/**
 * Created by yidatec on 2016/4/28.
 */
public class LocationVO {
    private String customerName;
    private Date date;
    private BMapPoint location;
    private String userName;
    private String userId;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BMapPoint getLocation() {
        return location;
    }

    public void setLocation(BMapPoint location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
