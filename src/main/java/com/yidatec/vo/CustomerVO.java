package com.yidatec.vo;

import com.yidatec.model.Contact;
import com.yidatec.model.Customer;

import java.util.List;

/**
 * @author xf
 */
public class CustomerVO extends Customer{
    private Integer draw;
    private Integer length;
    private Integer start;

    private List<Contact> userList;

    public List<Contact> getUserList() {
        return userList;
    }

    public void setUserList(List<Contact> userList) {
        this.userList = userList;
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
}
