package com.yidatec.model;

import java.util.List;

/**
 * @author jrw
 */


public class Param extends BaseModel {

    private String value;
    private String title;
    private List<Param> paramList;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Param> getParamList() {
        return paramList;
    }

    public void setParamList(List<Param> paramList) {
        this.paramList = paramList;
    }
}
