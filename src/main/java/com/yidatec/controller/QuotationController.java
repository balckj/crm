package com.yidatec.controller;

import com.yidatec.model.Quotation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/13.
 */
@Controller
public class QuotationController extends BaseController{

    @RequestMapping("/quotationList")
    public  String quotationList(){
        return  "quotationList";
    }

    @RequestMapping("/quotationEdit")
    public String quotationEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建报价单":"编辑报价单");
        return "quotationEdit";
    }

    @RequestMapping("/saveQuotation")
    @ResponseBody
    public Object saveQuotation(@Validated @RequestBody Quotation quotation,
                              BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        if(quotation.getId() == null || quotation.getId().trim().length() <= 0)//新建
        {
            quotation.setId(UUID.randomUUID().toString());
            quotation.setCreatorId(getWebUser().getId());
            quotation.setCreateTime(LocalDateTime.now());
            quotation.setModifierId(quotation.getCreatorId());
            quotation.setModifyTime(quotation.getCreateTime());
//            quotationService.createFactory(quotation);

        } else {//编辑
            quotation.setModifierId(getWebUser().getCreatorId());
            quotation.setModifyTime(LocalDateTime.now());
//            quotationService.updateFactory(quotation);
        }
        return getSuccessJson(null);
    }

    @RequestMapping("/getProductName")
    @ResponseBody
    public String getProductName(){
        return "";
    }
}
