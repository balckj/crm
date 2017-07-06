package com.yidatec.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author QuShengWen
 */
public class Permission extends CommonModel{
    private String parentId;
    private String name;
    private String url;
    private String desc;
    private String icon;
    private int sort;

    private List<Role> roleList;
    private List<Permission> children;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }


    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public boolean addRole(Role role){
        if (roleList == null)
            roleList = new ArrayList<Role>(10);
        return this.roleList.add(role);
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    public boolean addChildren(Permission child) {
        if (children == null)
            children = new ArrayList<Permission>(10);
        return this.children.add(child);
    }
}
