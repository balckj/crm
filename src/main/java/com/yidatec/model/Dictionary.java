package com.yidatec.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 字典实体类
 * @author jrw
 *
 */
public class Dictionary extends BaseModel {
	private static final long serialVersionUID = -140219923846563098L;
	@NotBlank(message = "必须输入主键", groups = { })
	@Length(min = 3, max = 40, message = "姓名必须由3到40个字符组成", groups = { })
	private String code;
	@NotBlank(message = "必须输入序号且为数字", groups = { })
	@Pattern(regexp="^[0-9]}$",message="序号格式不正确", groups = { })
	private int sort;
	@NotBlank(message = "必须输入项目值", groups = { })
	@Length(min = 3, max = 40, message = "项目值必须由3到40个字符组成", groups = { })
	private String value;
	@NotBlank(message = "必须输入描述", groups = { })
	@Length(min = 3, max = 40, message = "描述必须由3到40个字符组成", groups = { })
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
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
}
