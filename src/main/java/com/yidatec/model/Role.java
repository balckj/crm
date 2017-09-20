package com.yidatec.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author QuShengWen
 */
public class Role extends BaseModel{
    @NotBlank(message = "必须输入角色名称")
    @Length(min = 1, max = 20, message = "角色名称必须由1到20个字符组成")
    private String name;
    private String desc;
    private Integer state;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public int hashCode(){
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(obj  == null)return false;
        if(!(obj instanceof Role))return false;
        if(this == obj)return true;
        return getId().equals(((Role)obj).getId());
    }
}
