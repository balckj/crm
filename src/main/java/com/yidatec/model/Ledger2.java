package com.yidatec.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * 台账实体类
 * @author jrw
 *
 */
public class Ledger2 extends BaseModel {
	private static final long serialVersionUID = -140219923846563098L;
	@NotBlank(message = "必须输入款项类型", groups = { })
	@Length(max = 36, message = "款项类型最大不能超过36个字符", groups = { })
	private String moneyType;

	@NotBlank(message = "必须输入支付方式", groups = { })
	@Length(max = 36, message = "支付方式最大不能超过36个字符", groups = { })
	private String paymentMethod;

	@NotBlank(message = "必须输入成本中心", groups = { })
	@Length(max = 36, message = "成本中心最大不能超过36个字符", groups = { })
	private String costCenter;


	@NotBlank(message = "必须输入付款时间", groups = { })
	private String paymentTime;

	@NotNull(message = "必须输入付款金额", groups = { })
	@Digits(integer = 18 ,message = "付款金额价整数位不能超过18位,小数位必须是两位", fraction = 2 )
	private Float paymentAmount;

	@NotBlank(message = "必须输入经办人", groups = { })
	@Length(max = 30, message = "经办人最大不能超过30个字符", groups = { })
	private String operator;

	@NotBlank(message = "必须输入变更原因", groups = { })
	@Length(max = 100, message = "经办人最大不能超过100个字符", groups = { })
	private String reasonForChange;

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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
}
