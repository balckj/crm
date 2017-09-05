package com.yidatec.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.model.Quotation;
import com.yidatec.util.CustomLocalDateSerializer;

import java.time.LocalDate;

/**
 * Created by jrw on 2017/8/25.
 */
public class QuotationVO extends Quotation {
    private String id;
    private String DicId;

    private String categoryName;// 类别
    private String productName;// 产品名
    private String unitName;// 单位
    private String countPrice;// 合价

    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate startTime;
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate endTime;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCountPrice() {
        return countPrice;
    }

    public void setCountPrice(String countPrice) {
        this.countPrice = countPrice;
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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getDicId() {
        return DicId;
    }

    public void setDicId(String dicId) {
        DicId = dicId;
    }
}
