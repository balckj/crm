package com.yidatec.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2017/8/3.
 */
public class Case extends BaseModel {
    @NotBlank(message = "必须输入案例名称", groups = { })
    @Length( max = 100, message = "名称最多由100个字符组成", groups = { })
    private String name;

//    @NotBlank(message = "必须上传案例照片", groups = { })
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
