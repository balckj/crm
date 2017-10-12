package com.yidatec.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
public class Customer extends BaseModel{
    @NotBlank(message = "必须选择客户来源", groups = { })
    private String source;

    @NotBlank(message = "必须输入企业名称", groups = { })
    @Length(min = 1, max = 200, message = "企业名称必须由1到200个字符组成", groups = { })
    private String name;

    @NotBlank(message = "必须选择所属行业", groups = { })
    private String industry;
    @NotBlank(message = "必须选择企业性质", groups = { })
    private String nature;
    @NotBlank(message = "必须选择所在国家", groups = { })
    private String country;
//    @NotBlank(message = "必须选择所在省份", groups = { })
    private String province;
//    @NotBlank(message = "必须选择所在城市", groups = { })
    private String city;
//    @NotBlank(message = "必须选择所在区域", groups = { })
    private String region;

    @Length(max = 200, message = "地址必须最多由200个字符组成", groups = { })
    private String address;
    @NotBlank(message = "必须选择平台等级", groups = { })
    private String level;

    private Integer state;

    private String mediaIds;
    private String photo;

    @Valid
    private List<Contact> contactList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public String getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(String mediaIds) {
        this.mediaIds = mediaIds;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
