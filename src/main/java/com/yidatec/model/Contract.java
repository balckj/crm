package com.yidatec.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yidatec.util.CustomLocalDateSerializer;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by jrw on 2017/8/15.
 * 合同Model
 */
public class Contract extends BaseModel {

    @NotNull(message = "必须输入合同签署日期", groups = { })
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate signDay;

//    @NotBlank(message = "必须输入合同名称", groups = { })
//    @Length(max = 200, message = "合同名称最大不能超过200个字符", groups = { })
    private String name;

    @NotBlank(message = "必须输入合同编号", groups = { })
    @Length(max = 30, message = "合同编码最大不能超过30个字符", groups = { })
    private String code;
    @NotBlank(message = "必须输入项目编号", groups = { })
    @Length(max = 36, message = "项目编号最大不能超过36个字符", groups = { })
    private String projectId;
    @NotBlank(message = "必须输入项目名称", groups = { })
    @Length(max = 200, message = "项目名称最大不能超过200个字符", groups = { })
    private String projectName;
    @NotBlank(message = "必须输入市场活动", groups = { })
    @Length(max = 36, message = "市场活动最大不能超过36个字符", groups = { })
    private String campaignId;
    @NotBlank(message = "必须输入展位号", groups = { })
    @Length(max = 36, message = "展位号最大不能超过36个字符", groups = { })
    private String exhibitionNumber;
    @NotBlank(message = "必须输入面积", groups = { })
    @Length(max = 50, message = "面积最大不能超过50个字符", groups = { })
    private String area;
    @NotNull(message = "必须输入总价", groups = { })
    @Digits(integer = 18 ,message = "总价整数位不能超过18位,小数位必须是两位", fraction = 2 )
    private BigDecimal amount;
    @NotBlank(message = "必须输合同分类", groups = { })
    @Length(max = 36, message = "合同分类最大不能超过36个字符", groups = { })
    private String category;
//    @NotNull(message = "必须输入税", groups = { })
//    @Digits(integer = 18 ,message = "税整数位不能超过18位,小数位必须是两位", fraction = 2 )
    private Float tax;
    private Integer billing; // 开票状态 1:开票0:未开票
    @NotBlank(message = "必须输入付款方式", groups = { })
    @Length(max = 36, message = "付款方式最大不能超过36个字符", groups = { })
    private String contractPaymentMethod;
    @NotNull(message = "必须输入首付时间", groups = { })
    private LocalDate initialPaymentTime;
    @NotNull(message = "必须输入中款时间", groups = { })
    private LocalDate middlePaymentTime;
    @NotNull(message = "必须输入尾款时间", groups = { })
    private LocalDate finalPaymentTime;
    @NotBlank(message = "必须输入合同甲方", groups = { })
    @Length(max = 36, message = "合同甲方最大不能超过36个字符", groups = { })
    private String firstParty;
    @NotBlank(message = "必须输入合同乙方", groups = { })
    @Length(max = 36, message = "合同乙方最大不能超过36个字符", groups = { })
    private String secondParty;

    private String remarks;

    private String company;
    private String taxNumber;
    private String addressTelephone;
    private String bankAccount;

//    @NotNull(message = "必须输入台账", groups = {})
//    @Size(min=1,message = "必须输入台账")
//    private List<Ledger> ledgerListInput;

//    @NotBlank(message = "必须输入台账", groups = { })
//    private String ledgerInput;

    public LocalDate getSignDay() {
        return signDay;
    }

    public void setSignDay(LocalDate signDay) {
        this.signDay = signDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public String getContractPaymentMethod() {
        return contractPaymentMethod;
    }

    public void setContractPaymentMethod(String contractPaymentMethod) {
        this.contractPaymentMethod = contractPaymentMethod;
    }

    public LocalDate getInitialPaymentTime() {
        return initialPaymentTime;
    }

    public void setInitialPaymentTime(LocalDate initialPaymentTime) {
        this.initialPaymentTime = initialPaymentTime;
    }

    public LocalDate getMiddlePaymentTime() {
        return middlePaymentTime;
    }

    public void setMiddlePaymentTime(LocalDate middlePaymentTime) {
        this.middlePaymentTime = middlePaymentTime;
    }

    public LocalDate getFinalPaymentTime() {
        return finalPaymentTime;
    }

    public void setFinalPaymentTime(LocalDate finalPaymentTime) {
        this.finalPaymentTime = finalPaymentTime;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getSecondParty() {
        return secondParty;
    }

    public void setSecondParty(String secondParty) {
        this.secondParty = secondParty;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

//    public List<Ledger> getLedgerListInput() {
//        return ledgerListInput;
//    }
//
//    public void setLedgerListInput(List<Ledger> ledgerListInput) {
//        this.ledgerListInput = ledgerListInput;
//    }
//
//    public String getLedgerInput() {
//        return ledgerInput;
//    }
//
//    public void setLedgerInput(String ledgerInput) {
//        this.ledgerInput = ledgerInput;
//    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getAddressTelephone() {
        return addressTelephone;
    }

    public void setAddressTelephone(String addressTelephone) {
        this.addressTelephone = addressTelephone;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Integer getBilling() {
        return billing;
    }

    public void setBilling(Integer billing) {
        this.billing = billing;
    }
}
