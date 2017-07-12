package com.yidatec.controller;

import com.yidatec.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/7/11.
 */
@Controller
public class CustomerController extends BaseController{

    @RequestMapping("/customerList")
    public String customerList(ModelMap model){
        return "customerList";
    }

    @RequestMapping("/activities")
    public String activities(ModelMap model){
        return "activities";
    }


    @RequestMapping("/customerEdit")
    public String customerEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建客户":"编辑客户");

        return "customerEdit";
    }

    @RequestMapping("/activityEdit")
    public String activityEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建活动":"编辑活动");

        return "activityEdit";
    }
}
