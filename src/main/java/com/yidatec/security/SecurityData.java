package com.yidatec.security;

import com.yidatec.mapper.PermissionMapper;
import com.yidatec.mapper.RoleMapper;
import com.yidatec.mapper.RolePermissionMapper;
import com.yidatec.model.Permission;
import com.yidatec.model.Role;
import com.yidatec.model.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author QuShengWen
 */
@Service
public class SecurityData {

//    @Autowired
//    UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    private Map<String,Role> allRoleMap;

    private List<Role> roleList;

    private Map<String,Permission> allPermissionMap;

    private Collection<Permission> allPermission;

    public Map<String,Role> findAllRole(){
        if(allRoleMap == null){
            roleList = roleMapper.findEnable();
            if(roleList != null){
                allRoleMap = new TreeMap<String,Role>();
                for(Role role : roleList){
                    allRoleMap.put(role.getId(),role);
                }
            }
        }
        return allRoleMap;
    }

    public List<Role> findRoleListEnable(){
        if(roleList == null){
            findAllRole();
        }
        return roleList;
    }

    public Map<String,Permission> findAllPermission(){
        if(allPermissionMap == null){
            List<Permission> permissionList = permissionMapper.findAll();
            if(permissionList != null){
                allPermissionMap = new HashMap<String,Permission>(permissionList.size()*2);
                for(Permission permission : permissionList){
                    allPermissionMap.put(permission.getId(),permission);
                }
            }
        }
        return allPermissionMap;
    }

    private void populate(){
        List<RolePermission> rolePermissionList = rolePermissionMapper.findAll();
        if(rolePermissionList != null) {
            for(RolePermission rp : rolePermissionList){
                Permission p = allPermissionMap.get(rp.getPermissionId());
                Role tmp = allRoleMap.get(rp.getRoleId());
                if(tmp != null) {//如果角色非可用状态，将是null，用户将不再具有相应角色的权限
                    p.addRole(tmp);
                }
            }
        }
    }

    public Collection<Permission> findAllPermissionWithRole(){
        if(allPermission == null) {
            findAllPermission();
            findAllRole();
            populate();
            allPermission = allPermissionMap.values();
        }
        return allPermission;
    }

    public void refresh(){
        if(allPermission != null){
            allPermission.clear();
            allPermission = null;
        }
        if(allPermissionMap != null){
            allPermissionMap.clear();
            allPermissionMap = null;
        }
        if(roleList != null){
            roleList.clear();
            roleList = null;
        }
        if(allRoleMap != null){
            allRoleMap.clear();
            allRoleMap = null;
        }

    }
}
