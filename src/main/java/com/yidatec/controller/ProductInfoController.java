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
 * Created by Administrator on 2017/7/10.
 */
@Controller
public class ProductInfoController extends BaseController{

    @RequestMapping("/productInfoEdit")
    public String productInfoEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        return "productInfoEdit";
    }

    @RequestMapping("/productInfoList")
    public String productInfoList(ModelMap model){
        return "productInfoList";
    }

    @RequestMapping("/saveProductInfo")
    @ResponseBody
    public Object saveproductInfo(@Validated @RequestBody User userParam,
                           BindingResult result)throws Exception{
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findProductInfo")
    @ResponseBody
    public Object findproductInfo(@RequestBody UserVO user)throws Exception{
        return null;
    }
}
