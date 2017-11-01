package com.yidatec.controller;

import com.yidatec.mapper.UserCaseMapper;
import com.yidatec.mapper.UserMapper;
import com.yidatec.model.Dictionary;
import com.yidatec.model.User;
import com.yidatec.model.UserValidateDesigner;
import com.yidatec.service.DesignerService;
import com.yidatec.service.DictionaryService;
import com.yidatec.service.SaleService;
import com.yidatec.util.ConfProperties;
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
 * Created by jrw on 时间2017-08-04.
 */
@Controller
public class DesignerController extends BaseController{


    @Autowired
    SaleService saleService;

    @Autowired
    DesignerService designerService;

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserCaseMapper userCaseMapper;

    @Autowired
    ConfProperties confProperties;

    @RequestMapping("/designerList")
    public String designerList(ModelMap model){
        model.put("platformLevelList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PLATFORM_LEVEL)); // 平台等级
        model.put("designerCategoryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.DESIGNER_CATEGORY)); // 设计师分类
        return "designerList";
    }

    @RequestMapping("/designerEdit")
    public String designerEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建设计师":"编辑设计师");
        model.put("designer",designerService.selectDesigner(id));
        model.put("saleList",saleService.selectSaleListALL(new UserVO()));// 推荐人
        model.put("englishAbilityList",dictionaryService.selectDictionaryListByCodeCommon(Constants.ENGLISH_ABILITY));// 英文水平
        model.put("goodAtIndustryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_INDUSTRY_CODE));// 擅长行业
        model.put("goodAtAreaList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_AREA)); // 擅长面积
        model.put("designStyleList",dictionaryService.selectDictionaryListByCodeCommon(Constants.DESIGN_STYLE)); // 设计风格
        model.put("platformLevelList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PLATFORM_LEVEL)); // 平台等级
        model.put("designerCategoryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.DESIGNER_CATEGORY)); // 设计师分类
        model.put("designerCaseListFlg",userCaseMapper.getCaseList(id) != null && userCaseMapper.getCaseList(id).size()  > 0 ? true :false);
        model.put("designerCaseList",userCaseMapper.getCaseList(id));
        return "designerEdit";
    }

    @RequestMapping("/designerCaseList")
    @ResponseBody
    public Object designerCaseList(ModelMap model,@RequestParam(value="id",required = false) String id){
       HashMap<String,Object> map = new HashMap<String,Object>();
       map.put("designerCaseList",userCaseMapper.getCaseList(id));
       return map;
    }

    @RequestMapping("/saveDesigner")
    @ResponseBody
    public Object saveDesigner(@Validated({UserValidateDesigner.class}) @RequestBody User designer,
                                     BindingResult result)throws Exception{

        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        if(designer.getId() == null || designer.getId().trim().length() <= 0) {//新建
            designer.setId(UUID.randomUUID().toString().toLowerCase());
            designer.setCreatorId(getWebUser().getId());
            designer.setCreateTime(LocalDateTime.now());
            designer.setModifierId(getWebUser().getId());
            designer.setModifyTime(designer.getCreateTime());
            designerService.createDesigner(designer);
        } else {
            designer.setModifierId(getWebUser().getId());
            designer.setModifyTime(LocalDateTime.now());
            designerService.updateDesigner(designer);
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findDesigner")
    @ResponseBody
    public Object findDesigner(@RequestBody UserVO userVO)throws Exception{
        List<User> designerEntityList = designerService.selectDesignerListByNameOrPhone(userVO);
        if (designerEntityList != null){
            for(User u : designerEntityList){
                Dictionary dictionaryEn = dictionaryService.selectDictionary(u.getEnglishAbility());
                if(dictionaryEn != null){
                    u.setEnglishAbility(dictionaryEn.getValue());// 英文水平
                }

                Dictionary dictionaryArea = dictionaryService.selectDictionary(u.getGoodAtArea());
                if(dictionaryArea != null){
                    u.setGoodAtArea(dictionaryEn.getValue());// 擅长面积
                }
                Dictionary dictionaryDesignerCategory = dictionaryService.selectDictionary(u.getDesignerCategory());
                if(dictionaryDesignerCategory != null){
                    u.setDesignerCategory(dictionaryDesignerCategory.getValue());// 擅长设计类型
                }
                Dictionary dictionaryPLeve = dictionaryService.selectDictionary(u.getPlatformLevel());
                if(dictionaryPLeve != null){
                    u.setPlatformLevel(dictionaryPLeve.getValue());// 平台级别
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
        int count = designerService.countDesignerListByNameOrPhone(userVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", userVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", designerEntityList);
        return map;
    }
}
