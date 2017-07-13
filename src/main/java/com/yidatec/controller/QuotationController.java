package com.yidatec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/7/13.
 */
@Controller
public class QuotationController {
    @RequestMapping("/quotationList")
    public  String quotationList(){
        return  "quotationList";
    }

    @RequestMapping("/quotationEdit")
    public String quotationEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建报价单":"编辑报价单");
        return "quotationEdit";
    }
}
