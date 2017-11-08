package com.yidatec.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.model.BaseModel;
import com.yidatec.model.Exhibition;
import com.yidatec.util.CustomLocalDateSerializer;
import com.yidatec.util.CustomLocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author jrw
 */
public class ExhibitionVO extends BaseModel{
    private Integer draw;
    private Integer length;
    private Integer start;

    private String name;
    private String address;
    private String country;
    private String province;
    private String city;
    private String region;
    private String area;
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate startTime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
