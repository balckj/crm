package com.yidatec.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yidatec.mapper.RoleMapper;
import com.yidatec.mapper.UserMapper;
import com.yidatec.model.*;
import com.yidatec.security.AuthUtil;
import com.yidatec.security.MyInvocationSecurityMetadataSource;
import com.yidatec.security.SecurityData;
import com.yidatec.service.SecurityService;
import com.yidatec.util.ConfProperties;
import com.yidatec.util.HttpClientHelper;
import com.yidatec.util.WeixinHelper;
import com.yidatec.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/2/21.
 */
@Controller
public class SecurityController extends BaseController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;



    @Autowired
    SecurityService securityService;

    @Autowired
    SecurityData securityData;

    @Autowired
    AuthUtil authUtil;

    @Autowired
    ConfProperties confProperties;

    @Autowired
    MyInvocationSecurityMetadataSource myInvocationSecurityMetadataSource;

    @RequestMapping("/skin-config")
    public String skin(ModelMap model){
        return "skin-config";
    }

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(ModelMap model){
        User user = getWebUser();
        if(user == null)
            return "login";
        model.put("user",user);
        return "index";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(ModelMap model){
        User user = getWebUser();
        if(user == null)
            return getErrorJson(null);
        model.put("user",user);
        return getSuccessJson(null);
    }



    @RequestMapping("/userEdit")
    public String userEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建用户":"编辑用户");
        model.put("roleList",securityData.findRoleListEnable());
        if( id != null && !id.isEmpty()) {
            User user = userMapper.findById(id);
            model.put("user", user);
        }else{
            model.put("user", null);
        }
        model.put("isAdmin",id == null ? false:confProperties.getAdminId().equalsIgnoreCase(id));
        return "userEdit";
    }

    @RequestMapping("/userList")
    public String userList(ModelMap model){
        model.put("roleList",securityData.findRoleListEnable());
        return "userList";
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public Object saveUser(@Validated(UserValidate.class) @RequestBody User userParam,
                             BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }

        User user = getWebUser();
//        User user = new User();
//        user.setId("creatorId");

        if(userParam.getId() == null || userParam.getId().trim().length() <= 0)//新建
        {
            userParam.setId(UUID.randomUUID().toString().toLowerCase());
            userParam.setCreatorId(user.getId());
            userParam.setCreateTime(LocalDateTime.now());
            userParam.setModifierId(userParam.getCreatorId());
            userParam.setModifyTime(userParam.getCreateTime());
            securityService.createUser(userParam);
        } else {
            userParam.setModifierId(user.getId());
            userParam.setModifyTime(LocalDateTime.now());
            securityService.updateUser(userParam,false);
        }
        return getSuccessJson(null);

    }

//    @RequestMapping("/updateUserIngoreRole")
//    @ResponseBody
//    public Object updateUserIngoreRole(@Valid @RequestBody User userParam,
//                           BindingResult result)throws Exception{
//        List<FieldError> errors = result.getFieldErrors();
//        if(errors  != null && errors.size() > 0){
//            return errors;
//        }
//
//        User user = getWebUser();
//
//        userParam.setModifierId(user.getId());
//        userParam.setModifyTime(LocalDateTime.now());
//        securityService.updateUser(userParam,true);
//        return getSuccessJson(null);
//
//    }


    @RequestMapping(value = "/findUser")
    @ResponseBody
    public Object findUser(@RequestBody UserVO user)throws Exception{
        List<UserVO> userList = userMapper.findUsers(user);
        int count = userMapper.countUsers(user);

        List<Role> roleList = roleMapper.findAll();
        Map<String,Role> roleMap = new HashMap<String,Role>(roleList.size()*2);
        if(roleMap != null){
            for(Role r : roleList){
                roleMap.put(r.getId(),r);
            }
        }
//        Map<String,Role> roleMap = roleMapper.findAll();
            if(userList != null){
                for(UserVO item : userList){
                    String roleIds = item.getRoleIds();
                    if(roleIds != null){
                        String[] roles = roleIds.split(",");
                        if(roles != null){
                            for(String roleId : roles){
                                Role r = roleMap.get(roleId);
                                if(r != null) {
                                    item.setRoleNames((item.getRoleNames() == null ? "" : item.getRoleNames()) + r.getName() + "， ");
                                }
                            }
                        }
                    }
                    if(item.getRoleNames()!=null && item.getRoleNames().length()>0)
                        item.setRoleNames(item.getRoleNames().substring(0,item.getRoleNames().length()-2));
                }
            }



        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", user.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", userList);
        return map;
    }


    @RequestMapping("/roleEdit")
    public String roleEdit(ModelMap model,@RequestParam(value="id",required = false) String id){

        model.put("title",(id == null || id.isEmpty())?"新建角色":"编辑角色");
        Role role = roleMapper.findById(id);
        model.put("role",role);
        model.put("isAdminRole",id == null ? false:confProperties.getAdminRoleId().equalsIgnoreCase(id));
        return "roleEdit";
    }

    /**
     * 载入指定节点下的组织结构树形数据结构
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findPermissionTreeByRoleId")
    public Object loadTreeById(@RequestParam(value = "roleId", required = true) String roleId) {

        List<JsTreeVO> list = securityService.loadTreePermission(roleId);

        return list;
    }

    @RequestMapping("/saveRole")
    @ResponseBody
    public Object saveRole(@Valid @RequestBody RoleVO roleParam,
                           BindingResult result)throws Exception{

        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }

        User user = getWebUser();
//        User user = new User();
//        user.setId("creatorId");

        if(roleParam.getId() == null || roleParam.getId().trim().length() <= 0)//新建
        {
            roleParam.setId(UUID.randomUUID().toString().toLowerCase());
            roleParam.setCreatorId(user.getId());
            roleParam.setCreateTime(LocalDateTime.now());
            roleParam.setModifierId(roleParam.getCreatorId());
            roleParam.setModifyTime(roleParam.getCreateTime());
            securityService.createRole(roleParam,roleParam.getPermissions());
            myInvocationSecurityMetadataSource.refresh();
        } else {
            roleParam.setModifierId(user.getId());
            roleParam.setModifyTime(LocalDateTime.now());
            securityService.updateRole(roleParam,roleParam.getPermissions());
            myInvocationSecurityMetadataSource.refresh();
        }
        return getSuccessJson(null);

    }

    @RequestMapping("/roleList")
    public String roleList(ModelMap model){
        return "roleList";
    }

    @RequestMapping(value = "/findRole")
    @ResponseBody
    public Object findRole()throws Exception{
        List<Role> roleList = roleMapper.findAll();
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("draw", roleList.size());
        map.put("recordsTotal", roleList.size());
        map.put("recordsFiltered", roleList.size());
        map.put("data", roleList);
        return map;
    }

    @RequestMapping(value = "/retriveOpenId")
    public String retriveOpenId(HttpServletRequest request,ModelMap model,
                                @RequestParam(value = "code", required = false) String code,
                                @RequestParam(value = "state", required = false) String state) throws Exception{
        // 1,retrieve openId from qq, then retrieve userName(mobileNumber) by
        // openId,put openId and userName to model.
        // 2,retrieve openId from qq,put openId to model.

        // 授权取得OPENID
        String codestr = "https://api.weixin.qq.com/sns/oauth2/access_token?"
                + "appid=" + WeixinHelper.appId
                + "&secret=" +WeixinHelper.appSecret
                + "&code=" + code + "&grant_type=authorization_code";
        String jsonstr = HttpClientHelper
                .callTrustHttps(codestr);

        JsonNode node = new ObjectMapper().readTree(jsonstr);
        JsonNode openIdNode = node == null ? null : node.findValue("openid");
        String openId = openIdNode == null? null:openIdNode.asText();


        User dbUser = userMapper.findByOpenId(openId);

        if(dbUser == null){
            String queryString = "?openId="+openId;//+"&avatar="+headimgurl;
            String url = "/module/" + state + ".html#/list"  /*+state*/ + queryString;
            return "redirect:"+url;//将被路由到绑定画面
        }else{

            String queryString = "?id="+dbUser.getId();//+"&gender="+dbUser.getGender()/*+"&nick="+URLEncoder.encode(dbUser.getNick(),"UTF-8")*/+"&name="+((dbUser.getName() == null || dbUser.getName().isEmpty()) ? "":URLEncoder.encode(dbUser.getName(),"UTF-8"))+"&day="+LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"&avatar="+dbUser.getAvatar();
            String url = "/module/" + state + ".html#/list" /*+state*/ + queryString;

            try {
                authUtil.decide(dbUser, url);
            }catch(Exception ex){
                url = "/module/" + state + ".html#/accessDenial";
            }
            return "redirect:"+url;
        }
    }

    @RequestMapping("/registry/{module}")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Object registryUser(@PathVariable("module") String module,@Validated({UserValidateBind.class }) @RequestBody User userParam,
                               BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        int res = userMapper.bind(userParam);
        if(res ==0){
            return getErrorJson("手机号码或密码错误");
        }
        User dbUser = userMapper.findByMobilePhone(userParam.getMobilePhone());
        String url = "/module/" + module + ".html";
        try {
            authUtil.decide(dbUser, url);
        }catch(Exception ex){
            return getResultJson(2,null);
        }
        Map<String,Object> oo = new HashMap<String,Object>();
        oo.put("res",1);
        oo.put("data",dbUser);
        return oo;

    }

    @RequestMapping("/invalidateSession")
    public String invalidateSession(){
        return "invalidateSession";
    }

}
