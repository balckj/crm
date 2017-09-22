package com.yidatec.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.util.CustomLocalDateTime2Serializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PerformanceReportVO{
    private String projectId;
    private String contractId;
    private String contractCode;
    private String contractCategory;
    private String projectName;
    private String campaignName;
    private String campaignType;
    @JsonSerialize(using = CustomLocalDateTime2Serializer.class)
    private LocalDateTime startDate;
    @JsonSerialize(using = CustomLocalDateTime2Serializer.class)
    private LocalDateTime endDate;
    private String campaignStartEndTime;
    private String exhibitionNumber;
    private String contractCountAmount; // 合同总价
    private String contractCountAmountChange; // 合同总价变更
    private String customerChargeback; // 客户扣款
    private String chargebackReason; // 扣款理由
    private String address;
    private String costCenter;
    private String actualerformance; // 实际业绩
    private String maolirun; // 毛利润
    private String Percentage; // 百分比

    private String customerName;
    private String customerSource;
    private String contractSecondParty;

    private String contractCreatorId;
    private String projectCreatorId;

    private String pmId;
    private String developSaleId;
    private String traceSaleId;

    private String customerCreatorId;
    private String projectArea;

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

    public String getContractCategory() {
        return contractCategory;
    }

    public void setContractCategory(String contractCategory) {
        this.contractCategory = contractCategory;
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

    public String getContractCountAmount() {
        return contractCountAmount;
    }

    public void setContractCountAmount(String contractCountAmount) {
        this.contractCountAmount = contractCountAmount;
    }

    public String getContractCountAmountChange() {
        return contractCountAmountChange;
    }

    public void setContractCountAmountChange(String contractCountAmountChange) {
        this.contractCountAmountChange = contractCountAmountChange;
    }

    public String getCustomerChargeback() {
        return customerChargeback;
    }

    public void setCustomerChargeback(String customerChargeback) {
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

    public String getActualerformance() {
        return actualerformance;
    }

    public void setActualerformance(String actualerformance) {
        this.actualerformance = actualerformance;
    }

    public String getMaolirun() {
        return maolirun;
    }

    public void setMaolirun(String maolirun) {
        this.maolirun = maolirun;
    }

    public String getPercentage() {
        return Percentage;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public String getContractSecondParty() {
        return contractSecondParty;
    }

    public void setContractSecondParty(String contractSecondParty) {
        this.contractSecondParty = contractSecondParty;
    }

    public String getContractCreatorId() {
        return contractCreatorId;
    }

    public void setContractCreatorId(String contractCreatorId) {
        this.contractCreatorId = contractCreatorId;
    }

    public String getProjectCreatorId() {
        return projectCreatorId;
    }

    public void setProjectCreatorId(String projectCreatorId) {
        this.projectCreatorId = projectCreatorId;
    }

    public String getPmId() {
        return pmId;
    }

    public void setPmId(String pmId) {
        this.pmId = pmId;
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

    public String getCustomerCreatorId() {
        return customerCreatorId;
    }

    public void setCustomerCreatorId(String customerCreatorId) {
        this.customerCreatorId = customerCreatorId;
    }

    public String getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(String projectArea) {
        this.projectArea = projectArea;
    }
}
