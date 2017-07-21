package com.yidatec.service;

import com.yidatec.mapper.UserMapper;
import com.yidatec.model.Role;
import com.yidatec.model.User;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service("paramService")
public class ParamService {

    @Autowired
    UserMapper userMapper;

    public static final String IDLE_VENDER_ROLE = "SFSDF";
    public List<Role> findRoles(String fixedId){
        return null;
    }

    public List<User> findUsers(String fixedId){
        return null;
    }

    public List<UserVO> findUsersByRole(String roleId){
        return userMapper.findUsersByRole(roleId);
    }
}
