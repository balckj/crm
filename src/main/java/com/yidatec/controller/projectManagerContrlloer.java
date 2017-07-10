package com.yidatec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/7/10.
 */
@Controller
public class projectManagerContrlloer extends BaseController{
    @RequestMapping("/projectManagerIndex")
    public String projectManagerIndex(){
        return "prejectManager";
    }
}
