package com.yidatec.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 字典实体类
 * @author jrw
 *
 */
public class Sale extends BaseModel {
	private static final long serialVersionUID = -140219923846563098L;

	@NotBlank(message = "必须输入销售姓名", groups = { })
	@Length(max = 40, message = "销售姓名最大不能超过30个字符", groups = { })
	private String name;

	@NotBlank(message = "必须输入销售渠道代码", groups = { })
	@Length(max = 40, message = "销售渠道代码最大不能超过30个字符", groups = { })
	private String channel;

	@NotBlank(message = "必须输入手机号码", groups = { })
	@Pattern(regexp="^1[3|4|5|7|8][0-9]\\d{4,8}$",message="手机号码格式不正确", groups = { })
	private String mobilePhone;

	@NotBlank(message = "必须输入销售QQ邮箱", groups = { })
	@Pattern(regexp="^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",message="销售QQ邮箱格式不正确", groups = { })
	private String email;

	private Integer state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
