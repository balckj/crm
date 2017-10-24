package com.yidatec.vo;

import com.yidatec.model.BaseModel;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author jrw
 */


public class ParamVO extends BaseModel {

    @NotBlank(message = "必须选择空闲时间供应商", groups = { })
    private String vendorAppointment;
    @NotBlank(message = "必须选择设计师", groups = { })
    private String designer;
    @NotBlank(message = "必须选择项目经理", groups = { })
    private String pm;
    @NotBlank(message = "必须选择销售", groups = { })
    private String sale;

    public String getVendorAppointment() {
        return vendorAppointment;
    }

    public void setVendorAppointment(String vendorAppointment) {
        this.vendorAppointment = vendorAppointment;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
