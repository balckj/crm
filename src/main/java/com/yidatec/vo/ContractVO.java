package com.yidatec.vo;

import com.yidatec.model.Contract;

/**
 * Created by Administrator on 2017/7/21.
 */
public class ContractVO extends Contract{
    private Integer draw;
    private Integer length;
    private Integer start;

    private String projectCode;
    private String campaignName;

    private int ledgerCount;

    /**
     * 0,表示我的；1，表示所有的
     *
     */
    private int isAll;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public int getLedgerCount() {
        return ledgerCount;
    }

    public void setLedgerCount(int ledgerCount) {
        this.ledgerCount = ledgerCount;
    }

    public int getIsAll() {
        return isAll;
    }

    public void setIsAll(int isAll) {
        this.isAll = isAll;
    }
}
