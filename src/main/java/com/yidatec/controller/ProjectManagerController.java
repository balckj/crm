package com.yidatec.controller;

import com.yidatec.mapper.UserMapper;
import com.yidatec.model.Dictionary;
import com.yidatec.model.User;
import com.yidatec.model.UserValidatePM;
import com.yidatec.service.DictionaryService;
import com.yidatec.service.PMService;
import com.yidatec.service.SaleService;
import com.yidatec.util.Constants;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jrw on 2017/7/10.
 */
@Controller
public class ProjectManagerController extends BaseController{


    @Autowired
    SaleService saleService;

    @Autowired
    PMService pmService;

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/projectManagerList")
    public String projectManagerList(ModelMap model){
        return "projectManagerList";
    }

    @RequestMapping("/projectManagerEdit")
    public String projectManagerEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建经理":"编辑经理");
        model.put("pm",pmService.selectPM(id));
        model.put("saleList",saleService.selectSaleListALL(new UserVO()));// 推荐人
        model.put("englishAbilityList",dictionaryService.selectDictionaryListByCodeCommon(Constants.ENGLISH_ABILITY));// 英文水平
        model.put("goodAtIndustryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_INDUSTRY_CODE));// 擅长行业
        model.put("goodAtAreaList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_AREA)); // 擅长面积
        return "projectManagerEdit";
    }

    @RequestMapping("/saveProjectManager")
    @ResponseBody
    public Object saveProjectManager(@Validated({UserValidatePM.class}) @RequestBody User pm,
                                  BindingResult result)throws Exception{

        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        if(pm.getId() == null || pm.getId().trim().length() <= 0) {//新建
            pm.setId(UUID.randomUUID().toString().toLowerCase());
            pm.setCreatorId(getWebUser().getId());
            pm.setCreateTime(LocalDateTime.now());
            pm.setModifierId(getWebUser().getId());
            pm.setModifyTime(LocalDateTime.now());
            pmService.createSale(pm);
        } else {
            pm.setModifierId(getWebUser().getId());
            pm.setModifyTime(LocalDateTime.now());
            pmService.updateSale(pm);
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findProjectManager")
    @ResponseBody
    public Object findProjectManager(@RequestBody UserVO userVO)throws Exception{
        List<User> pmEntityList = pmService.selectPMListByNameOrPhone(userVO);
        if (pmEntityList != null){
            for(User u : pmEntityList){
                Dictionary dictionaryEn = dictionaryService.selectDictionary(u.getEnglishAbility());
                if(dictionaryEn != null){
                    u.setEnglishAbility(dictionaryEn.getValue());// 英文水平
                }

                Dictionary dictionaryArea = dictionaryService.selectDictionary(u.getGoodAtArea());
                if(dictionaryArea != null){
                    u.setGoodAtArea(dictionaryEn.getValue());// 擅长面积
                }

                String params = u.getGoodAtIndustry();
                if (!StringUtils.isEmpty(params)){
                    String[] paramsList = params.split(",");
                    String temp = "";
                    for(int i = 0 ; i < paramsList.length; i++){
                        Dictionary dictionary = dictionaryService.selectDictionary(paramsList[i]);
                        if(i != paramsList.length -1){
                            temp = temp + dictionary.getValue()  +",";
                        }else{
                            temp = temp + dictionary.getValue();
                        }
                    }
                    u.setGoodAtIndustry(temp);// 擅长行业
                }
            }
        }
        int count = pmService.countPMListByNameOrPhone(userVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", userVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", pmEntityList);
        return map;
    }
}
