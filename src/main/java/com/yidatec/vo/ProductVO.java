package com.yidatec.vo;

import com.yidatec.model.Product;

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
	private String search;

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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}
