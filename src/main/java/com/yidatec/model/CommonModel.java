package com.yidatec.model;

/**
 * @author QuShengWen
 */
public abstract class CommonModel implements IModel {
    private String id;//uuid

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
