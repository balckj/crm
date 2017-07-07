package com.yidatec.vo;

import com.yidatec.model.Role;

import java.util.List;

/**
 * @author QuShengWen
 */
public class RoleVO extends Role {

    private List<String> permissions;

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
