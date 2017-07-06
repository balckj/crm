package com.yidatec.vo;

/**
 * @author QuShengWen
 */
public class JsTreeVO {
    private String id;
    private String parent;
    private String text;
    private String icon;
    private Integer sort;
    private String desc;
    private State state;

    public JsTreeVO(String id, String parent, String text, String icon, Integer sort, String desc, State state) {
        this.id = id;
        this.parent = parent;
        this.text = text;
        this.icon = icon;
        this.sort = sort;
        this.desc = desc;
        this.state = state;
    }

    public JsTreeVO(String id, String parent, String text, String icon, Integer sort, String desc,Boolean opened,Boolean disabled,Boolean selected) {
        this.id = id;
        this.parent = parent;
        this.text = text;
        this.icon = icon;
        this.sort = sort;
        this.desc = desc;
        this.state = new State(disabled,selected,opened);
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setDisabled(boolean disabled){
        if(state != null)
            state.setDisabled(disabled);
    }
}

class State {
    private Boolean opened;
    private Boolean disabled;
    private Boolean selected;

    public State(Boolean disabled, Boolean selected, Boolean opened) {
        this.disabled = disabled;
        this.selected = selected;
        this.opened = opened;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
