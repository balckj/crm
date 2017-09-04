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

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String,List<Role>> FIND_ROLES_CACHE = null;
    public Map<String,List<UserVO>> FIND_USERS_CACHE = new HashMap<String,List<UserVO>>(200);
    public Map<String,Role> ALL_ROLE_CACHE = null;
    public Map<String,User> ALL_USER_CACHE = null;
//    @PostConstruct
    public void postRoleInist(){
        if(ALL_ROLE_CACHE == null){
            ALL_ROLE_CACHE = new HashMap<String,Role>();
            List<Role> roles = roleMapper.findAll();
            for (Role r :roles){
                ALL_ROLE_CACHE.put(r.getId(),r);
            }
        }
        findAllParam();
    }

    private void findAllParam(){

        if(FIND_ROLES_CACHE == null){
            FIND_ROLES_CACHE = new HashMap<>();
            List<Param> paramsList = paramMapper.findAllParam();
            for (Param p : paramsList){
                if( !StringUtils.isEmpty(p.getValue())){
                    String[] roleid = p.getValue().split(",");
                    List<Role> roles = new ArrayList<Role>();
                    for(String s : roleid){
                        Role role = ALL_ROLE_CACHE.get(s);
                        if (role != null) {
                            roles.add(role);
                        }
                    }
                    FIND_ROLES_CACHE.put(p.getId(),roles);
                }
            }
        }

    }

//    @PostConstruct
//    public void postUserInist(){
////        if(ALL_USER_CACHE == null){
////            ALL_USER_CACHE = new HashMap<String,User>();
////            List<User> users = userMapper.findAll();
////            for (User u :users){
////                ALL_USER_CACHE.put(u.getId(),u);
////            }
////        }
//        String fixedId = "1";
//        String fixedId2 = "2";
//        List<Role> roleLis = FIND_ROLES_CACHE.get(fixedId);
//        List<Role> roleLis = FIND_ROLES_CACHE.get(fixedId2);
//
//    }
//
//    private void findUserParam(String fixedId){
//        List<Role> roleLis = FIND_ROLES_CACHE.get(fixedId);
//
////        FIND_USERS_CACHE.put()
//    }

    public List<Role> findRoles(String fixedId){
        postRoleInist();
        return FIND_ROLES_CACHE.get(fixedId);
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
        paramMapper.clear();
        if(param.getParamList() !=null && param.getParamList().size() > 0){
            for (int i = 0; i < param.getParamList().size(); i++){
                Param param1 = param.getParamList().get(i);
                paramMapper.create(param1);
            }
        }
        refresh();
    }

    public void refresh(){
        if(ALL_ROLE_CACHE != null){
            ALL_ROLE_CACHE.clear();
            ALL_ROLE_CACHE = null;
        }
        if(FIND_ROLES_CACHE != null){
            FIND_ROLES_CACHE.clear();
            FIND_ROLES_CACHE = null;
        }
        postRoleInist();
    }
}
