package com.yidatec.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 字典实体类
 * @author jrw
 *
 */
public class Dictionary extends BaseModel {
	private static final long serialVersionUID = -140219923846563098L;
	@NotBlank(message = "必须输入主键", groups = { })
	@Length(min = 3, max = 40, message = "主键必须由3到40个字符组成", groups = { })
	private String code;

	@NotNull(message = "必须输入大于等于0的整数", groups = {})
	@Min(value=0,message = "必须输入大于等于0的整数", groups = {})
	private Integer sort;

	@NotBlank(message = "必须输入项目值", groups = { })
	@Length(min = 1, max = 50, message = "项目值必须由1到50个字符组成", groups = { })
	private String value;

	@NotBlank(message = "必须输入描述", groups = { })
	@Length(min = 1, max = 10, message = "描述必须由1到10个字符组成", groups = { })
	private String description;

	// 1:可用0:不可用
	private Integer state;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
