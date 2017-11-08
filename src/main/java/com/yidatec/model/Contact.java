package com.yidatec.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by Administrator on 2017/7/25.
 */
public class Contact extends BaseModel{
    @NotBlank(message = "必须输入联系人", groups = { })
    @Length(max = 30, message = "联系人最多超过30个字符组成", groups = { })
    private String name;

    @NotBlank(message = "必须输入联系人电话", groups = { })
    @Pattern(regexp="^1[3|4|5|7|8][0-9]\\d{4,8}$",message="手机号码格式不正确", groups = { })
    private String mobilePhone;
    @NotBlank(message = "必须输入联系人职位", groups = { })
    private String position;
//    @Email(regexp="^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",message="邮箱格式不正确", groups = { })
    private String email;


    private String qq;
    private String tel;
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
