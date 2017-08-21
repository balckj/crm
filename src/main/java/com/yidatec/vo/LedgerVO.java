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

	@Valid
	private List<Ledger> ledgerList;

	public List<Ledger> getLedgerList() {
		return ledgerList;
	}

	public void setLedgerList(List<Ledger> ledgerList) {
		this.ledgerList = ledgerList;
	}
}
