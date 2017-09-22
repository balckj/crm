package com.yidatec.vo;

import com.yidatec.model.User;

/**
 * Created by qsw on 17-9-21.
 */
public class DesignerReportVO extends User {

    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
