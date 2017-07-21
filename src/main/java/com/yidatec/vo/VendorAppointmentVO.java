package com.yidatec.vo;

import com.yidatec.model.VendorAppointment;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author jrw
 */
public class VendorAppointmentVO{
    private String vendorId;
    private String title;
    private Integer allDay;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
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
}
