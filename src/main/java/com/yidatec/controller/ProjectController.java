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
 * Created by neo on 2017/7/12.
 */
@Controller
public class ProjectController extends BaseController {

    @RequestMapping("/projectEdit")
    public String projectEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        return "projectEdit";
    }

    @RequestMapping("/projectList")
    public String projectList(ModelMap model){
        return "projectList";
    }

    @RequestMapping("/saveProject")
    @ResponseBody
    public Object saveProject(@Validated @RequestBody User userParam,
                            BindingResult result)throws Exception{
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findProject")
    @ResponseBody
    public Object findProject(@RequestBody UserVO user)throws Exception{
        return null;
    }
}
