package com.yidatec.vo;

import com.yidatec.model.BaseModel;
import com.yidatec.model.Product;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * 产品实体类
 * @author jrw
 *
 */
public class ProductVO extends Product {
	private static final long serialVersionUID = -140219923846563098L;
	private Integer draw;
	private Integer length;
	private Integer start;

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
}
