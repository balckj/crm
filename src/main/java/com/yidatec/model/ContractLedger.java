package com.yidatec.model;

/**
 * Created by jrw on 2017/8/3.
 */
public class ContractLedger extends BaseModel {

    private String contractId;
    private String ledgerId;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId;
    }
}
