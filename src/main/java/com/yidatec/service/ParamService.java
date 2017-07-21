package com.yidatec.service;

import com.yidatec.mapper.ParamMapper;
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


    public Param findParam(String id){
        return paramMapper.findParamById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void create(Param param){
        paramMapper.delete(param.getId());
        paramMapper.create(param);
    }
}
