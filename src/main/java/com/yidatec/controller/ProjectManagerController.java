package com.yidatec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by neo on 2017/7/10.
 */
@Controller
public class ProjectManagerController {

    @RequestMapping("/projectManagerIndex")
    public String projectManagerIndex(){
        return "projectManager";
    }
}
