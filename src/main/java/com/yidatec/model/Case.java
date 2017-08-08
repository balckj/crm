package com.yidatec.model;

/**
 * Created by Administrator on 2017/8/3.
 */
public class Case extends BaseModel {

    private String name;
    private String photo;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
