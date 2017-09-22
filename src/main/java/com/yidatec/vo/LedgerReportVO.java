package com.yidatec.vo;

import com.yidatec.model.Ledger;

/**
 * Created by qsw on 17-9-21.
 */
public class LedgerReportVO extends Ledger {
    private String contractId;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
}
