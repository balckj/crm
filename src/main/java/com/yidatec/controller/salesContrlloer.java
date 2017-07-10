package com.yidatec.controller;

import com.yidatec.model.Role;
import com.yidatec.model.User;
import com.yidatec.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/10.
 */
@Controller
public class salesContrlloer extends BaseController{
    @RequestMapping("/salesIndex")
    public String salesIndex(){
        return "salesList";
    }

    @RequestMapping("/salesEdit")
    public String salesEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        return "salesEdit";
    }

    @RequestMapping("/salesList")
    public String salesList(ModelMap model){
        return "salesList";
    }

    @RequestMapping("/saveSales")
    @ResponseBody
    public Object saveSales(@Validated @RequestBody User userParam,
                           BindingResult result)throws Exception{
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findSales")
    @ResponseBody
    public Object findSales(@RequestBody UserVO user)throws Exception{
        return null;
    }
}
