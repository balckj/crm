package com.yidatec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/7/27.
 */
@Controller
public class DesignerController {
    @RequestMapping("/designerList")
    public String designerList(ModelMap model){
        return "designerList";
    }

    @RequestMapping("/designerEdit")
    public String designerEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建设计师":"编辑设计师");
        return "designerEdit";
    }
}
