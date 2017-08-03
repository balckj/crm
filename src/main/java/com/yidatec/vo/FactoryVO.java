package com.yidatec.vo;

import com.yidatec.model.FactoryEntity;

/**
 * Created by Administrator on 2017/7/27.
 */
public class FactoryVO extends FactoryEntity{
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
