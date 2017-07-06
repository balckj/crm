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


    @NotBlank(message = "必须输入姓名", groups = { })
    @Length(min = 2, max = 40, message = "姓名必须由2到40个字符组成", groups = { })
    private String name;
    private String avatar;
    //用户以电话号码标识，如果没有注册电话号码就无法约课，电话号码是必须的
    @NotBlank(message = "必须输入手机号码", groups = { })
    @Pattern(regexp="^1[3|4|5|7|8][0-9]\\d{4,8}$",message="手机号码格式不正确", groups = { })
    private String mobilePhone;
    @Email(message="邮箱格式不正确", groups = { })
    private String email;
    private String openId;
    @NotBlank(message = "必须输入密码", groups = { })
    @Pattern(regexp="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,10}",message="密码必须是5~10位数字和字母的组合", groups = { })
    private String password;

    private Integer gender;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate birthday;


    private Integer state;


//    @JsonIgnore
    private List<Role> roleList;

    @JsonIgnore
    private List<GrantedAuthority> grantedAuthorityList;


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


}
