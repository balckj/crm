package com.yidatec.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.util.CustomLocalDateSerializer;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

/**
 * 观众实体类
 * @author jrw
 *
 */
public class Audience extends BaseModel {
	private static final long serialVersionUID = -140219923846563098L;

	@NotBlank(message = "必须输入姓名", groups = {})
	@Length(min = 2, max = 40, message = "姓名必须由2到40个字符组成", groups = {})
	private String name;

	@NotBlank(message = "必须输入手机号码", groups = {})
	@Pattern(regexp="^1[3|4|5|7|8][0-9]\\d{4,8}$",message="手机号码格式不正确", groups = {})
	private String mobilePhone;

	@NotBlank(message = "必须输入邮箱", groups = {})
	@Email(message="邮箱格式不正确", groups = {})
	private String email;

	@NotBlank(message = "必须输入兴趣爱好", groups = {})
	private String hobby;

	@NotBlank(message = "必须输入国家", groups = {})
	private String country;
	private String address;
	private String province;
	private String city;
	private String region;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
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
}
