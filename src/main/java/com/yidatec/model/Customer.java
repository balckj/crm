package com.yidatec.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by Administrator on 2017/7/11.
 */
public class Customer extends BaseModel{
    @NotBlank(message = "必须输入企业名称", groups = { })
    @Length(min = 3, max = 200, message = "企业名称必须由3到200个字符组成", groups = { })
    private String companyName;

//    @NotBlank(message = "必须输入企业ID", groups = { })
//    @Length(min = 2, max = 20, message = "企业ID必须由3到20个字符组成", groups = { })
    private String companyId;

    @NotBlank(message = "必须选择所属行业", groups = { })
    private String industry;
    @NotBlank(message = "必须选择企业性质", groups = { })
    private String nature;
    @NotBlank(message = "必须选择所在国家", groups = { })
    private String country;
    @NotBlank(message = "必须选择所在省份", groups = { })
    private String province;
    @NotBlank(message = "必须选择所在城市", groups = { })
    private String city;
    @NotBlank(message = "必须选择所在区域", groups = { })
    private String region;

    @Length(max = 200, message = "地址必须由3到200个字符组成", groups = { })
    private String address;
    @NotBlank(message = "必须选择平台等级", groups = { })
    private String level;

//    @NotBlank(message = "必须输入联系人", groups = { })
    @Length(min =1, max = 30, message = "联系人必须由3到30个字符组成", groups = { })
    private String userName;

    @Length(min = 3, max = 30, message = "联系人职位必须由3到30个字符组成", groups = { })
    private String userPosition;

//    @NotBlank(message = "必须输入联系人电话", groups = { })
    @Pattern(regexp="^1[3|4|5|7|8][0-9]\\d{4,8}$",message="手机号码格式不正确", groups = { })
    private String userPhone;

//    @NotBlank(message = "必须输入联系人QQ邮箱", groups = { })
    @Email(message="邮箱格式不正确", groups = { })
    private String userEmail;
    private Integer state;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
