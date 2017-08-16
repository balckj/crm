package com.yidatec.model;

import java.time.LocalDate;

/**
 * Created by Administrator on 2017/8/15.
 */
public class Contract extends BaseModel {
    private LocalDate signDay;
    private String name;
    private String code;
    private String projectId;
    private String campaignId;
    private String exhibitionNumber;
    private Float area;
    private Float amount;
    private String category;
    private Float tax;
    private String paymentMethod;
    private LocalDate initialPaymentTime;
    private LocalDate middlePaymentTime;
    private LocalDate finalPaymentTime;
    private String billingInfo;
    private String firstParty;
    private String secondParty;
    private String remark;

}
