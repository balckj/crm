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
public class AvailableSuppliersListController extends BaseController{
    @RequestMapping("/availableSuppliersIndex")
    public String availableSuppliersIndex(){
        return "AvailableSuppliersList";
    }

    @RequestMapping("/availableSuppliersList")
    public String availableSuppliersList(ModelMap model){
        return "availableSuppliersList";
    }

    @RequestMapping("/availableSuppliersEdit")
    public String availableSuppliersEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        return "availableSuppliersEdit";
    }

    @RequestMapping("/saveAvailableSuppliers")
    @ResponseBody
    public Object saveAvailableSuppliers(@Validated @RequestBody User userParam,
                            BindingResult result)throws Exception{
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findAvailableSuppliers")
    @ResponseBody
    public Object findAvailableSuppliers(@RequestBody UserVO user)throws Exception{
        return null;
    }
}
