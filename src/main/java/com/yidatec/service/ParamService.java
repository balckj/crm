package com.yidatec.service;

import com.yidatec.mapper.ParamMapper;
import com.yidatec.mapper.RoleMapper;
import com.yidatec.mapper.UserMapper;
import com.yidatec.model.Param;
import com.yidatec.model.Role;
import com.yidatec.model.User;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service("paramService")
public class ParamService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ParamMapper paramMapper;
    @Autowired
    RoleMapper roleMapper;

//    public static final String IDLE_VENDER_ROLE = "SFSDF";
    public List<Role> findRoles(String fixedId){
        List<Role> rolesList = new ArrayList<Role>();
        Param paramRole = paramMapper.findParamById(fixedId);
        if(paramRole != null && StringUtils.isEmpty(paramRole.getValue()) && paramRole.getValue().indexOf(",") > 0){
            String[] roleid = paramRole.getValue().split(",");
            for(String s : roleid){
                rolesList.add(roleMapper.findById(s));
            }
        }
        return rolesList;
    }

    public List<UserVO> findUsers(String fixedId){
        List<Role> roles = findRoles(fixedId);
        List<UserVO> userAll = new ArrayList<UserVO>();
        for(int i = 0; i < roles.size(); i++){
            userAll.addAll(findUsersByRole(roles.get(i).getId()));
        }
        return userAll;
    }

    public List<UserVO> findUsersByRole(String roleId){
        return userMapper.findUsersByRole(roleId);
    }


    public Param findParam(String id){
        return paramMapper.findParamById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void create(Param param){
        paramMapper.delete(param.getId());
        paramMapper.create(param);
    }
}
