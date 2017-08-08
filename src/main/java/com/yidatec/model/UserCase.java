package com.yidatec.model;

/**
 * Created by jrw on 2017/8/3.
 */
public class UserCase extends BaseModel {

    private String designerId;
    private String caseId;

    public String getDesignerId() {
        return designerId;
    }

    public void setDesignerId(String designerId) {
        this.designerId = designerId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
}
