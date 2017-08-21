package com.yidatec.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.util.CustomLocalDateSerializer;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */
public class ProjectEntity extends BaseModel {
    @NotBlank(message = "必须输入项目名称", groups = { })
    @Length( max = 200, message = "项目名称最多由200个字符组成", groups = { })
    private String name;

    @NotBlank(message = "必须选择类型", groups = { })
    private String type;

    @NotBlank(message = "必须选择客户", groups = { })
    private String customerId;

    @NotBlank(message = "必须选择市场活动", groups = { })
    private String campaignId;//市场活动

    private String code;

    @NotNull(message = "必须输入预算", groups = { })
    @Digits(integer = 18 ,message = "预算整数位不能超过18位,小数位必须是两位", fraction = 2 /*scale*/)
    private Float budget;

    @NotBlank(message = "必须输入展位号", groups = { })
    @Length( max = 30, message = "展位号最多由30个字符组成", groups = { })
    private String exhibitionNumber;

    @NotBlank(message = "必须输入面积", groups = { })
    @Length( max = 30, message = "面积最多由30个字符组成", groups = { })
    private String area;

    @NotNull(message = "必须输入搭建时间", groups = { })
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate setupTime;

    @NotNull(message = "必须输入撤展时间", groups = { })
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate tearDownTime;

    @NotNull(message = "必须输入项目成本", groups = { })
    @Digits(integer = 18 ,message = "成本整数位不能超过18位,小数位必须是两位", fraction = 2 /*scale*/)
    private Float cost;

    @NotBlank(message = "必须选择重要程度", groups = { })
    private String degreeOfImportance;

    @NotBlank(message = "必须选择项目潜力", groups = { })
    private String potential;

//    @NotBlank(message = "必须选择设计师", groups = { })
    private List<String> designers;


    private String designProgress;

    @NotBlank(message = "必须选择项目经理", groups = { })
    private String pmId;

    private String projectProgress;

//    @NotBlank(message = "必须选择工厂", groups = { })
    private List<String> factories;

    private String factoryProgress;
    private Float projectScore;
    private Float designScore;
    private Float pmScore;
    @NotBlank(message = "必须选择开发销售", groups = { })
    private String developSaleId;
    private String traceSaleId;
    private String photo;
    private Integer state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }

    public String getExhibitionNumber() {
        return exhibitionNumber;
    }

    public void setExhibitionNumber(String exhibitionNumber) {
        this.exhibitionNumber = exhibitionNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDate getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(LocalDate setupTime) {
        this.setupTime = setupTime;
    }

    public LocalDate getTearDownTime() {
        return tearDownTime;
    }

    public void setTearDownTime(LocalDate tearDownTime) {
        this.tearDownTime = tearDownTime;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getDegreeOfImportance() {
        return degreeOfImportance;
    }

    public void setDegreeOfImportance(String degreeOfImportance) {
        this.degreeOfImportance = degreeOfImportance;
    }

    public String getPotential() {
        return potential;
    }

    public void setPotential(String potential) {
        this.potential = potential;
    }

    public String getDesignProgress() {
        return designProgress;
    }

    public void setDesignProgress(String designProgress) {
        this.designProgress = designProgress;
    }

    public String getPmId() {
        return pmId;
    }

    public void setPmId(String pmId) {
        this.pmId = pmId;
    }

    public String getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress;
    }

    public List<String> getDesigners() {
        return designers;
    }

    public void setDesigners(List<String> designers) {
        this.designers = designers;
    }

    public List<String> getFactories() {
        return factories;
    }

    public void setFactories(List<String> factories) {
        this.factories = factories;
    }

    public String getFactoryProgress() {
        return factoryProgress;
    }

    public void setFactoryProgress(String factoryProgress) {
        this.factoryProgress = factoryProgress;
    }

    public Float getProjectScore() {
        return projectScore;
    }

    public void setProjectScore(Float projectScore) {
        this.projectScore = projectScore;
    }

    public Float getDesignScore() {
        return designScore;
    }

    public void setDesignScore(Float designScore) {
        this.designScore = designScore;
    }

    public Float getPmScore() {
        return pmScore;
    }

    public void setPmScore(Float pmScore) {
        this.pmScore = pmScore;
    }

    public String getDevelopSaleId() {
        return developSaleId;
    }

    public void setDevelopSaleId(String developSaleId) {
        this.developSaleId = developSaleId;
    }

    public String getTraceSaleId() {
        return traceSaleId;
    }

    public void setTraceSaleId(String traceSaleId) {
        this.traceSaleId = traceSaleId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
