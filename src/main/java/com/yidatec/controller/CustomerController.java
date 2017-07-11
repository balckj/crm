package com.yidatec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/factoryList")
    public String factoryList(ModelMap model){
        return "factoryList";
    }

    @RequestMapping("/designerList")
    public String designerList(ModelMap model){
        return "designerList";
    }
}
