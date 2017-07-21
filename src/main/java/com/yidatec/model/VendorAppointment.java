package com.yidatec.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.util.CustomLocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 字典实体类
 * @author jrw
 *
 */
public class VendorAppointment extends BaseModel {
	private static final long serialVersionUID = -140219923846563098L;

	private String vendorId;
	private String title;
	private Integer allDay;
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime start;
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime end;
	private Integer type;
	private boolean editable;
	private boolean durationEditable;
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAllDay() {
		return allDay;
	}

	public void setAllDay(Integer allDay) {
		this.allDay = allDay;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isDurationEditable() {
		return durationEditable;
	}

	public void setDurationEditable(boolean durationEditable) {
		this.durationEditable = durationEditable;
	}

	public String getClassName(){
		if(!editable)
			return "one-item-disabled";
		return "";
	}
}
