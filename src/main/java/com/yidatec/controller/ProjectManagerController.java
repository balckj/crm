package com.yidatec.controller;

import com.yidatec.model.Sale;
import com.yidatec.model.User;
import com.yidatec.service.DictionaryService;
import com.yidatec.service.SaleService;
import com.yidatec.util.Constants;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProjectManagerController extends BaseController{


    @Autowired
    SaleService saleService;

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping("/projectManagerList")
    public String projectManagerList(ModelMap model){

        return "projectManagerList";
    }

    @RequestMapping("/projectManagerEdit")
    public String projectManagerEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("saleList",saleService.selectSaleListALL(new UserVO()));// 推荐人
        model.put("englishAbilityList",dictionaryService.selectDictionaryListByCodeCommon(Constants.ENGLISH_ABILITY));// 英文水平
        model.put("goodAtIndustryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_INDUSTRY_CODE));// 擅长行业
        model.put("goodAtAreaList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_AREA)); // 擅长面积
        return "projectManagerEdit";
    }



    @RequestMapping("/saveProjectManager")
    @ResponseBody
    public Object saveProjectManager(@Validated @RequestBody User userParam,
                                  BindingResult result)throws Exception{

        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findProjectManager")
    @ResponseBody
    public Object findProjectManager(@RequestBody UserVO user)throws Exception{
        return null;
    }
}
