package com.yidatec.vo;

import com.yidatec.model.BaseModel;
import com.yidatec.model.Ledger;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 台账实体类
 * @author jrw
 *
 */
public class LedgerVO extends BaseModel {
	private static final long serialVersionUID = -140219923846563098L;

	// 台账保存的时候验证用
	@Valid
	private List<Ledger> ledgerList;

	// 台账编辑的时候显示用
	private String category;
	private String moneyType;
	private String moneyTypeName;
	private String paymentMethod;
	private String paymentMethodName;
	private String costCenter;
	private String costCenterName;
	private String paymentTime;
	private Float paymentAmount;
	private String operator;
	private String reasonForChange;


	public List<Ledger> getLedgerList() {
		return ledgerList;
	}

	public void setLedgerList(List<Ledger> ledgerList) {
		this.ledgerList = ledgerList;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getMoneyTypeName() {
		return moneyTypeName;
	}

	public void setMoneyTypeName(String moneyTypeName) {
		this.moneyTypeName = moneyTypeName;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public Float getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getReasonForChange() {
		return reasonForChange;
	}

	public void setReasonForChange(String reasonForChange) {
		this.reasonForChange = reasonForChange;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
