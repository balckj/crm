package com.yidatec.controller;

import com.yidatec.model.User;
import com.yidatec.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by neo on 2017/7/11.
 */
@Controller
public class SupplierFreeController extends BaseController{
    @RequestMapping("/supplierFreeList")
    public String supplierFreeList(ModelMap model){
        return "supplierFreeList";
    }

    @RequestMapping("/supplierFreeEdit")
    public String supplierFreeEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        return "supplierFreeEdit";
    }



    @RequestMapping("/saveSupplierFree")
    @ResponseBody
    public Object saveSupplierFree(@Validated @RequestBody User userParam,
                            BindingResult result)throws Exception{
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findSupplierFree")
    @ResponseBody
    public Object findSupplierFree(@RequestBody UserVO user)throws Exception{
        return null;
    }
}
