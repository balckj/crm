package com.yidatec.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.util.CustomLocalDateTimeSerializer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Exhibition extends BaseModel{
    @NotBlank(message = "必须输入展馆名称", groups = { })
    private String name;

//    @NotNull(message = "必须输入开展时间", groups = { })
//    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate startTime;

    @NotBlank(message = "必须输入展馆地址", groups = { })
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public LocalDate getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(LocalDate startTime) {
//        this.startTime = startTime;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
