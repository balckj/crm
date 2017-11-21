package com.yidatec.model;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */
public class FollowHistoryVO extends BaseModel{


    @Valid
    private List<FollowHistory> historyList;

    public List<FollowHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<FollowHistory> historyList) {
        this.historyList = historyList;
    }


}
