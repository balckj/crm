package com.yidatec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/7/12.
 */
@Controller
public class AudienceController {
    @RequestMapping("/audienceList")
    public String audienceList(){
        return "audienceList";
    }

    @RequestMapping("/audienceEdit")
    public String audienceEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建观众":"编辑观众");
        return "audienceEdit";
    }
}
