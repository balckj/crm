package com.yidatec.model;

/**
 * Created by Administrator on 2017/8/3.
 */
public class Case extends BaseModel {

    private String caseName;
    private String casePhoto;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCasePhoto() {
        return casePhoto;
    }

    public void setCasePhoto(String casePhoto) {
        this.casePhoto = casePhoto;
    }
}
