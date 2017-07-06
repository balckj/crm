package com.yidatec.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.util.CustomLocalDateTimeSerializer;

import java.time.LocalDateTime;

/**
 * @author QuShengWen
 */
public abstract class Model implements IModel{
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime createTime;
    private String creatorId;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime modifyTime;
    private String modifierId;


    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }
}
