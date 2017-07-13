package com.yidatec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/7/13.
 */
@Controller
public class ContractController {
    @RequestMapping("/contractList")
    public String contractList(){
        return "contractList";
    }

    @RequestMapping("/contractEdit")
    public String contractEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建合同":"编辑合同");
        return "contractEdit";
    }
}
