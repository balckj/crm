package com.yidatec.controller;

import com.yidatec.mapper.RoleMapper;
import com.yidatec.mapper.UserMapper;
import com.yidatec.model.Role;
import com.yidatec.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author QuShengWen
 */
@RestController
public class ValidateController extends BaseController{

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @RequestMapping(value = "/validateUserName")
    public String validateName(@RequestParam(value="name") String name){
        if(name == null || name.trim().isEmpty()){
            return getErrorJson("必须输入真实姓名");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateMobile")
    public String validateMobilePhone(@RequestParam(value="mobile") String mobile,@RequestParam(value="isEdit")boolean isEdit){
        if(mobile == null || mobile.trim().isEmpty()){
            return getErrorJson("必须输入手机号码");
        }
        mobile = mobile.trim();
        boolean res = mobile.matches("^1[3|4|5|7|8][0-9]\\d{4,8}$");
        if(!res)
            return getErrorJson("手机号码格式不正确");
        else if(!isEdit){
            User user = userMapper.findByMobilePhone(mobile);
            if(user != null)
                return getErrorJson("手机号码已存在");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validatePassword")
    public String validatePassword(@RequestParam(value="password") String password){
        if(password == null || password.trim().isEmpty()){
            return getErrorJson("必须输入密码");
        }
        password = password.trim();
        boolean res = password.matches("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,10}");
        if(!res)
            return getErrorJson("密码必须是5~10位数字和字母的组合");
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateInteger")
    public String validateInteger(@RequestParam(value="integer") String integer){
        if(integer == null)return getSuccessJson(null);

        integer = integer.trim();
        if(integer.isEmpty())return getSuccessJson(null);
        try{
            Integer.parseInt(integer);
        }catch(Exception ex){
            return getErrorJson("必须输入整数");
        }

        return getSuccessJson(null);
    }


    @RequestMapping(value = "/validateFloat")
    public String validateFloat(@RequestParam(value="flo") String flo){
        if(flo == null)return getSuccessJson(null);

        flo = flo.trim();
        if(flo.isEmpty())return getSuccessJson(null);
        try{
            Float.parseFloat(flo);
        }catch(Exception ex){
            return getErrorJson("必须输入整数或小数");
        }

        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateEmail")
    public String validateEmail(@RequestParam(value="email") String email){
        if(email == null)return getSuccessJson(null);
        email = email.trim();
        if(email.isEmpty())return getSuccessJson(null);
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        if(!flag){
            return getErrorJson(" 邮箱格式不正确");
        }

        return getSuccessJson(null);
    }



    @RequestMapping(value = "/validateRoleName")
    public String validateRoleName(@RequestParam(value="name") String name,@RequestParam(value="isEdit")boolean isEdit){
        if(name == null || name.trim().isEmpty()){
            return getErrorJson("必须输入角色名称");
        }

        if(!isEdit){
            Role role = roleMapper.findByName(name);
            if(role != null)
                return getErrorJson("角色名称已存在");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateCode")
    public String validateCode(@RequestParam(value="code") String code){
        if(code == null || code.trim().isEmpty()){
            return getErrorJson("必须输入主键");
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateSort")
    public String validateSort(@RequestParam(value="sort") String sort){
        if(sort == null || sort.trim().isEmpty()){
            return getErrorJson("必须输入序号");
        }
        sort = sort.trim();
        boolean res = sort.matches( "^[0-9]*[0-9][0-9]*$");
        if(!res)
            return getErrorJson("序号必须是数字");
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/validateValue")
    public String validateValue(@RequestParam(value="value") String value){
        if(value == null || value.trim().isEmpty()){
            return getErrorJson("必须输入项目值");
        }
        return getSuccessJson(null);
    }
    @RequestMapping(value = "/validateDescription")
    public String validateDescription(@RequestParam(value="description") String description){
        if(description == null || description.trim().isEmpty()){
            return getErrorJson("必须输入描述");
        }
        return getSuccessJson(null);
    }

}
