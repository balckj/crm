package com.yidatec.controller;

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

/**
 * Created by Administrator on 2017/7/10.
 */
@Controller
public class ProjectManagerController extends BaseController{


    @RequestMapping("/projectManagerList")
    public String projectManagerList(ModelMap model){
        return "projectManagerList";
    }
    
    @RequestMapping("/projectManagerEdit")
    public String projectManagerEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        return "projectManagerEdit";
    }



    @RequestMapping("/saveProjectManager")
    @ResponseBody
    public Object saveProjectManager(@Validated @RequestBody User userParam,
                                  BindingResult result)throws Exception{
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findProjectManager")
    @ResponseBody
    public Object findProjectManager(@RequestBody UserVO user)throws Exception{
        return null;
    }
}
