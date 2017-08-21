package com.yidatec.vo;

import com.yidatec.model.ProjectEntity;

/**
 * Created by Administrator on 2017/8/17.
 */
public class ProjectVO extends ProjectEntity{
    private Integer draw;
    private Integer length;
    private Integer start;

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
}
