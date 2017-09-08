package com.yidatec.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.util.CustomLocalDateSerializer;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author QuShengWen
 */


public class User extends BaseModel implements UserDetails {


    @NotBlank(message = "必须输入姓名", groups = {UserValidatePM.class,UserValidateDesigner.class,UserValidateSale.class,UserValidateSpectator.class ,UserValidate.class})
    @Length(min = 2, max = 40, message = "姓名必须由2到40个字符组成", groups = {UserValidatePM.class,UserValidateDesigner.class,UserValidateSale.class,UserValidateSpectator.class ,UserValidate.class })
    private String name;
    private String avatar;
    //用户以电话号码标识，如果没有注册电话号码就无法约课，电话号码是必须的
    @NotBlank(message = "必须输入手机号码", groups = {UserValidatePM.class,UserValidateDesigner.class,UserValidateSale.class,UserValidateSpectator.class ,UserValidate.class,UserValidateBind.class })
    @Pattern(regexp="^1[3|4|5|7|8][0-9]\\d{4,8}$",message="手机号码格式不正确", groups = {UserValidatePM.class,UserValidateDesigner.class,UserValidateSale.class,UserValidateSpectator.class ,UserValidate.class,UserValidateBind.class })
    private String mobilePhone;
    @NotBlank(message = "必须输入邮箱", groups = {UserValidatePM.class,UserValidateDesigner.class,UserValidateSale.class,UserValidateSpectator.class,UserValidate.class })
    @Email(message="邮箱格式不正确", groups = {UserValidatePM.class,UserValidateDesigner.class,UserValidateSale.class,UserValidateSpectator.class ,UserValidate.class })
    private String email;
    private String openId;
    @NotBlank(message = "必须输入密码", groups = {UserValidatePM.class,UserValidateDesigner.class,UserValidateSale.class,UserValidateSpectator.class ,UserValidate.class,UserValidateBind.class })
    @Pattern(regexp="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,10}",message="密码必须是5~10位数字和字母的组合", groups = { UserValidatePM.class,UserValidateDesigner.class,UserValidateSale.class,UserValidateSpectator.class ,UserValidate.class})
    private String password;

    private Integer gender;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate birthday;


    private Integer state;

    private String nameEN;

    @NotBlank(message = "必须输入推荐人", groups = {UserValidatePM.class,UserValidateDesigner.class })
    private String referrer;

    private String wechat;

    @NotBlank(message = "必须输入英文能力", groups = {UserValidatePM.class,UserValidateDesigner.class })
    private String englishAbility;

    @NotBlank(message = "必须输入擅长行业", groups = {UserValidatePM.class,UserValidateDesigner.class })
    private String goodAtIndustry;

    @NotBlank(message = "必须输入擅长面积", groups = {UserValidatePM.class,UserValidateDesigner.class })
    private String goodAtArea;

    @NotBlank(message = "必须输入设计风格", groups = {UserValidateDesigner.class })
    private String designStyle;

    @NotNull(message = "必须输入从业年限", groups = {UserValidatePM.class,UserValidateDesigner.class })
    @Min(value=0,message = "必须输入大于等于0的整数", groups = {})
    private Integer experience;

    @NotBlank(message = "必须输入最近上家公司", groups = {UserValidatePM.class,UserValidateDesigner.class })
    private String previous;

    @NotBlank(message = "必须输入平台级别", groups = {UserValidateDesigner.class })
    private String platformLevel;

    private String platformCreditLevel;

    @NotBlank(message = "必须输入销售渠道", groups = {UserValidateSale.class })
    private String channel;

    @NotBlank(message = "必须输入兴趣爱好", groups = {UserValidateSpectator.class })
    private String hobbies;


//    @JsonIgnore
    private List<Role> roleList;

    @JsonIgnore
    private List<GrantedAuthority> grantedAuthorityList;

    @NotBlank(message = "必须输入国家", groups = {UserValidatePM.class })
    private String country;
    private String address;
    private String province;
    private String city;
    private String region;

    private List<Case> caseList;//案例列表

    private int caseNumb;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * 1.可用，2.锁定，3.禁用
     */
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }


    @JsonIgnore
    public List<GrantedAuthority> getGrantedAuthorityList() {
        return grantedAuthorityList;
    }

    public void setGrantedAuthorityList(List<GrantedAuthority> grantedAuthorityList) {
        this.grantedAuthorityList = grantedAuthorityList;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (getGrantedAuthorityList() == null) {
            setGrantedAuthorityList(new ArrayList<GrantedAuthority>());
            for (Role role : getRoleList()) {
                getGrantedAuthorityList().add(new SimpleGrantedAuthority(role
                        .getName()));
            }
        }
        return getGrantedAuthorityList();
    }

//    @Override
//    public String getPassword() {
//        return getPwd();
//    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return getMobilePhone();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return getState() != 3;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return getState() != 2;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return getState() == 1;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEnglishAbility() {
        return englishAbility;
    }

    public void setEnglishAbility(String englishAbility) {
        this.englishAbility = englishAbility;
    }

    public String getGoodAtIndustry() {
        return goodAtIndustry;
    }

    public void setGoodAtIndustry(String goodAtIndustry) {
        this.goodAtIndustry = goodAtIndustry;
    }

    public String getGoodAtArea() {
        return goodAtArea;
    }

    public void setGoodAtArea(String goodAtArea) {
        this.goodAtArea = goodAtArea;
    }

    public String getDesignStyle() {
        return designStyle;
    }

    public void setDesignStyle(String designStyle) {
        this.designStyle = designStyle;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getPlatformLevel() {
        return platformLevel;
    }

    public void setPlatformLevel(String platformLevel) {
        this.platformLevel = platformLevel;
    }

    public String getPlatformCreditLevel() {
        return platformCreditLevel;
    }

    public void setPlatformCreditLevel(String platformCreditLevel) {
        this.platformCreditLevel = platformCreditLevel;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<Case> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<Case> caseList) {
        this.caseList = caseList;
    }

    public int getCaseNumb() {
        return caseNumb;
    }

    public void setCaseNumb(int caseNumb) {
        this.caseNumb = caseNumb;
    }
}
