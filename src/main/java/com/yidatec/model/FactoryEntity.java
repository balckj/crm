package com.yidatec.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public class FactoryEntity extends BaseModel{
    private String referrer;//推荐人
    private String factoryName;//企业名称
    private String director;//厂长
    private Contact contact;//联系人
    private LocalDate firstOrderTime;//首单时间
    private String country;
    private String province;
    private String city;
    private String region;
    private String address;
    private Float factoryArea;//厂房面积
    private List photo;
    private Float registeredCapital;//注册资金
    private String taxpayerType;//纳税人身份
    private Integer fixedEmployeeCount;//固定工人数量
    private String goodAtIndustry;//擅长行业
    private String goodAtMaterial;//擅长材料
    private String goodAtArea;//擅长面积
    private String platformLevel;//平台等级
    private String valueAddedTaxAccount;//增值税发票账号
    private String taxNumber;//税号
    private Integer state;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getFactoryName() {return factoryName;}

    public void setFactoryName(String factoryName) {this.factoryName = factoryName;}

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getFirstOrderTime() {
        return firstOrderTime;
    }

    public void setFirstOrderTime(LocalDate firstOrderTime) {
        this.firstOrderTime = firstOrderTime;
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

    public Float getFactoryArea() {
        return factoryArea;
    }

    public void setFactoryArea(Float factoryArea) {
        this.factoryArea = factoryArea;
    }

    public List getPhoto() {
        return photo;
    }

    public void setPhoto(List photo) {
        this.photo = photo;
    }

    public Float getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Float registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getTaxpayerType() {
        return taxpayerType;
    }

    public void setTaxpayerType(String taxpayerType) {
        this.taxpayerType = taxpayerType;
    }

    public Integer getFixedEmployeeCount() {
        return fixedEmployeeCount;
    }

    public void setFixedEmployeeCount(Integer fixedEmployeeCount) {
        this.fixedEmployeeCount = fixedEmployeeCount;
    }

    public String getGoodAtIndustry() {
        return goodAtIndustry;
    }

    public void setGoodAtIndustry(String goodAtIndustry) {
        this.goodAtIndustry = goodAtIndustry;
    }

    public String getGoodAtMaterial() {
        return goodAtMaterial;
    }

    public void setGoodAtMaterial(String goodAtMaterial) {
        this.goodAtMaterial = goodAtMaterial;
    }

    public String getGoodAtArea() {
        return goodAtArea;
    }

    public void setGoodAtArea(String goodAtArea) {
        this.goodAtArea = goodAtArea;
    }

    public String getPlatformLevel() {
        return platformLevel;
    }

    public void setPlatformLevel(String platformLevel) {
        this.platformLevel = platformLevel;
    }

    public String getValueAddedTaxAccount() {
        return valueAddedTaxAccount;
    }

    public void setValueAddedTaxAccount(String valueAddedTaxAccount) {
        this.valueAddedTaxAccount = valueAddedTaxAccount;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
