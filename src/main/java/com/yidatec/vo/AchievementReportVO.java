package com.yidatec.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.util.CustomLocalDateTime2Serializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AchievementReportVO {
    private String projectId;
    private String contractId;
    private String contractCode;
    private String projectName;
    private String campaignName;
    private String campaignType;
    @JsonSerialize(using = CustomLocalDateTime2Serializer.class)
    private LocalDateTime startDate;
    @JsonSerialize(using = CustomLocalDateTime2Serializer.class)
    private LocalDateTime endDate;
    private String campaignStartEndTime;
    private String exhibitionNumber;
    private Float contractCountAmount; // 合同总价
    private Float contractCountAmountChange; // 合同总价变更
    private Float customerChargeback; // 客户扣款
    private String chargebackReason; // 扣款理由
    private String address;
    private String costCenter;
    private Float actualerformance; // 实际业绩
    private Float maolirun; // 毛利润
    private String Percentage; // 百分比

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(String campaignType) {
        this.campaignType = campaignType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getCampaignStartEndTime() {
        return campaignStartEndTime;
    }

    public void setCampaignStartEndTime(String campaignStartEndTime) {
        this.campaignStartEndTime = campaignStartEndTime;
    }

    public String getExhibitionNumber() {
        return exhibitionNumber;
    }

    public void setExhibitionNumber(String exhibitionNumber) {
        this.exhibitionNumber = exhibitionNumber;
    }

    public Float getContractCountAmount() {
        return contractCountAmount;
    }

    public void setContractCountAmount(Float contractCountAmount) {
        this.contractCountAmount = contractCountAmount;
    }

    public Float getContractCountAmountChange() {
        return contractCountAmountChange;
    }

    public void setContractCountAmountChange(Float contractCountAmountChange) {
        this.contractCountAmountChange = contractCountAmountChange;
    }

    public Float getCustomerChargeback() {
        return customerChargeback;
    }

    public void setCustomerChargeback(Float customerChargeback) {
        this.customerChargeback = customerChargeback;
    }

    public String getChargebackReason() {
        return chargebackReason;
    }

    public void setChargebackReason(String chargebackReason) {
        this.chargebackReason = chargebackReason;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public Float getActualerformance() {
        return actualerformance;
    }

    public void setActualerformance(Float actualerformance) {
        this.actualerformance = actualerformance;
    }

    public Float getMaolirun() {
        return maolirun;
    }

    public void setMaolirun(Float maolirun) {
        this.maolirun = maolirun;
    }

    public String getPercentage() {
        return Percentage;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }
}
