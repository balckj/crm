package com.yidatec.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.model.AvailableSupplier;
import com.yidatec.util.CustomLocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AvailableSupplierVO extends AvailableSupplier {
    private Integer draw;
    private Integer length;
    private Integer start;

    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;

    private String availableSupplierType;

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

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getAvailableSupplierType() {
        return availableSupplierType;
    }

    public void setAvailableSupplierType(String availableSupplierType) {
        this.availableSupplierType = availableSupplierType;
    }
}
