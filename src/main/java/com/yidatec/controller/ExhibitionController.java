package com.yidatec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/7/12.
 */
@Controller
public class ExhibitionController {
    @RequestMapping("/exhibitionList")
    public String exhibitionList(){
        return "exhibitionList";
    }

    @RequestMapping("/exhibitionEdit")
    public String exhibitionEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建展馆":"编辑展馆");

        return "exhibitionEdit";
    }
}
