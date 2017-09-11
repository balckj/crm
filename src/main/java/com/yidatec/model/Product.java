package com.yidatec.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * 产品实体类
 * @author jrw
 *
 */
public class Product extends BaseModel {
	private static final long serialVersionUID = -140219923846563098L;
	@NotBlank(message = "必须输入产品分类", groups = { })
	@Length(max = 36, message = "产品分类最大不能超过36个字符", groups = { })
	private String category;

	@NotBlank(message = "必须输入产品名称", groups = { })
	@Length(max = 200, message = "产品名称最大不能超过200个字符", groups = { })
	private String name;

	@NotBlank(message = "必须输入详细描述", groups = {})
	@Length(max = 200, message = "详细藐视最大不能超过200个字符", groups = { })
	private String desc;

	@NotBlank(message = "必须输入单位", groups = { })
	@Length(max = 36, message = "单位最大不能超过36个字符", groups = { })
	private String unit;

	@NotBlank(message = "必须输入方式", groups = { })
	@Length(max = 36, message = "方式最大不能超过36个字符", groups = { })
	private String way;

	@NotNull(message = "必须输入低价", groups = { })
	@Digits(integer = 18 ,message = "低价整数位不能超过18位,小数位必须是两位", fraction = 2 /*scale*/)
	private Float low;

	@NotNull(message = "必须输入中价", groups = { })
	@Digits(integer = 18 ,message = "中价整数位不能超过18位,小数位必须是两位", fraction = 2 /*scale*/)
	private Float middle;

	@NotNull(message = "必须输入高价", groups = { })
	@Digits(integer = 18 ,message = "高价价整数位不能超过18位,小数位必须是两位", fraction = 2 /*scale*/)
	private Float high;

	private String unitPrice;

	private Integer count;

	private String workContent;

	private String produceId;

	public String getProduceId() {
		return produceId;
	}

	public void setProduceId(String produceId) {
		this.produceId = produceId;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public Float getLow() {
		return low;
	}

	public void setLow(Float low) {
		this.low = low;
	}

	public Float getMiddle() {
		return middle;
	}

	public void setMiddle(Float middle) {
		this.middle = middle;
	}

	public Float getHigh() {
		return high;
	}

	public void setHigh(Float high) {
		this.high = high;
	}
}
