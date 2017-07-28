package com.yidatec.service;

import com.yidatec.mapper.*;
import com.yidatec.model.*;
import com.yidatec.util.ConfProperties;
import com.yidatec.vo.JsTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author QuShengWen
 */
@Service("securityService")
public class SecurityService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

//    @Autowired
//    UserTrainerMapper userTrainerMapper;

    @Autowired
    ConfProperties confProperties;

    @Autowired
    ParamService paramService;

//    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
//    public void registryUser(User user){
//        int a = userMapper.generateSequence(user.getCode());
//        String sequence = "";
//        if(a<10){
//            sequence = "0000"+a;
//        }else if(a < 100){
//            sequence = "000"+a;
//        }else if(a < 1000){
//            sequence = "00"+a;
//        }else if(a < 10000){
//            sequence = "0"+a;
//        }
//        else{
//            sequence = ""+a;
//        }
//        user.setCode(user.getCode()+user.getCreateTime().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"))+sequence);
//        userMapper.registry(user);
//    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void createUser(User user){
        userMapper.create(user);
        List<Role> roleList = user.getRoleList();

        if(roleList != null || roleList.size() > 0){
            for(Role role : roleList){
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(role.getId());
                userRoleMapper.insertUserRole(userRole);
            }
        }

    }



    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void updateUser(User user,boolean ingoreRole){
        userMapper.update(user);
        if(ingoreRole)return;
        userRoleMapper.deleteUserRole(user.getId());
        List<Role> roleList = user.getRoleList();
        if(roleList != null || roleList.size() > 0){
            for(Role role : roleList){
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(role.getId());
                userRoleMapper.insertUserRole(userRole);
            }
        }

    }

    /**
     * 无需构建父子关系，只要设置了parentId属性，JsTree自己会构建
     * @param roleId
     * @return
     */
    public List<JsTreeVO> loadTreePermission(String roleId) {

        List<JsTreeVO> src = new ArrayList<JsTreeVO>();
        List<Permission> list = permissionMapper.findAll();
        List<Permission> rolePermissionList = null;
        if (roleId != null && !roleId.isEmpty()) {
            rolePermissionList = permissionMapper.findByRoleId(roleId);
        }
        List<String> selectedPermission = null;
        if (rolePermissionList != null) {
            selectedPermission = new ArrayList<String>();
            for (Permission perm : rolePermissionList) {
                selectedPermission.add(perm.getId());
            }
        }
        if (list != null && list.size() > 0) {


            Map<String, Permission> securityChildren = new HashMap<String, Permission>();
            for (Permission item : list) {
                boolean selected = false;
                boolean disabled = false;
                if (selectedPermission != null
                        && selectedPermission.contains(item.getId()))
                    selected = true;
                // 如果是系统安全节点及其子节点，并且是系统管理员角色，那么树节点永远是选中的且禁止改变
                if ((confProperties.getSecuritySettingId().equalsIgnoreCase(
                        item.getId()) || confProperties.getSecuritySettingId().equalsIgnoreCase(
                        item.getParentId()))
                        && confProperties.getAdminRoleId().equalsIgnoreCase(
                        roleId)) {
                    if (confProperties.getSecuritySettingId().equalsIgnoreCase(
                            item.getParentId())) {
                        securityChildren.put(item.getId(), item);
                    }
                    disabled = true;
                }
                String parentId = item.getParentId();
                if (item.getParentId().equalsIgnoreCase(confProperties.getResRoot()))
                    parentId = "#";
                JsTreeVO vo = new JsTreeVO(item.getId(), parentId, item.getName(), item.getIcon(), item.getSort(), item.getDesc(), false, disabled, selected);
                src.add(vo);
            }

            //设置用户编辑，角色编辑链接为禁止状态
            if (confProperties.getAdminRoleId().equalsIgnoreCase(
                    roleId)){
                for (JsTreeVO one : src) {
                    Object parent = securityChildren.get(one.getParent());
                    if(parent != null){
                        one.setDisabled(true);
                    }
                }
            }


        }

        return src;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void createRole(Role role,List<String> permissions) {
        roleMapper.create(role);
        if(permissions != null || permissions.size() > 0){
            for(String permissionId : permissions){
                RolePermission rp = new RolePermission();
                rp.setRoleId(role.getId());
                rp.setPermissionId(permissionId);
                rolePermissionMapper.insertRolePermission(rp);
            }
        }
        paramService.refresh();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void updateRole(Role role,List<String> permissions){
        roleMapper.update(role);
        rolePermissionMapper.deleteRolePermission(role.getId());
        if(permissions != null || permissions.size() > 0){
            for(String permissionId : permissions){
                RolePermission rp = new RolePermission();
                rp.setRoleId(role.getId());
                rp.setPermissionId(permissionId);
                rolePermissionMapper.insertRolePermission(rp);
            }
        }
        paramService.refresh();
    }

}
