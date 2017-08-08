package com.yidatec.controller;

import com.yidatec.model.Contact;
import com.yidatec.model.Dictionary;
import com.yidatec.model.FactoryEntity;
import com.yidatec.service.DictionaryService;
import com.yidatec.service.FactoryService;
import com.yidatec.service.SaleService;
import com.yidatec.util.Constants;
import com.yidatec.vo.FactoryVO;
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
 * Created by Administrator on 2017/7/12.
 */
@Controller
public class FactoryController extends BaseController{

    @Autowired
    FactoryService factoryService;

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    SaleService saleService;

    @RequestMapping("/factoryList")
    public String factoryList(ModelMap model){
        return "factoryList";
    }

    @RequestMapping("/factoryEdit")
    public String factoryEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建工厂":"编辑工厂");
        model.put("factory",factoryService.selectFactory(id));
        model.put("saleList",saleService.selectSaleListALL(new UserVO()));// 推荐人
        model.put("goodAtIndustryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_INDUSTRY_CODE));// 擅长行业
        model.put("goodAtMaterialList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_MATERIAL));// 擅长材料
        model.put("goodAtAreaList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_AREA)); // 擅长面积
        model.put("levelList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PLATFORM_LEVEL));// 平台等级
        return "factoryEdit";
    }

    @RequestMapping("/saveFactory")
    @ResponseBody
    public Object saveFactory(@Validated @RequestBody FactoryEntity factory,
                               BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        FactoryEntity factory1 = new FactoryEntity();
        Contact contact = new Contact();
        if(factory.getId() == null || factory.getId().trim().length() <= 0)//新建
        {
            factory.setId(UUID.randomUUID().toString());
            factory.setCreatorId(getWebUser().getId());
            factory.setCreateTime(LocalDateTime.now());
            factory.setModifierId(factory.getCreatorId());
            factory.setModifyTime(factory.getCreateTime());
            factoryService.createFactory(factory);

        } else {//编辑
            factory1.setId(factory.getId());
            factory1.setId(UUID.randomUUID().toString().toLowerCase());
            factory1.setReferrer(factory.getReferrer());
            factory1.setFactoryName(factory.getFactoryName());
            factory1.setDirector(factory.getDirector());
            factory1.setFirstOrderTime(factory.getFirstOrderTime());
            factory1.setUserList(factory.getUserList());
            factory1.setCountry(factory.getCountry());
            factory1.setProvince(factory.getProvince());
            factory1.setCity(factory.getCity());
            factory1.setRegion(factory.getRegion());
            factory1.setAddress(factory.getAddress());
            factory1.setFactoryArea(factory.getFactoryArea());
            factory1.setPhoto(factory.getPhoto());
            factory1.setRegisteredCapital(factory.getRegisteredCapital());
            factory1.setTaxpayerType(factory.getTaxpayerType());
            factory1.setFixedEmployeeCount(factory.getFixedEmployeeCount());
            factory1.setGoodAtIndustry(factory.getGoodAtIndustry());
            factory1.setGoodAtMaterial(factory.getGoodAtMaterial());
            factory1.setGoodAtArea(factory.getGoodAtArea());
            factory1.setPlatformLevel(factory.getPlatformLevel());
            factory1.setValueAddedTaxAccount(factory.getValueAddedTaxAccount());
            factory1.setTaxNumber(factory.getTaxNumber());
            factory1.setState(factory.getState());
            factory1.setCreatorId(getWebUser().getId());
            factory1.setCreateTime(LocalDateTime.now());
            factory1.setModifierId(getWebUser().getCreatorId());
            factory1.setModifyTime(LocalDateTime.now());
//            factoryService.updateFactory(factory1,getWebUser());
        }
        return getSuccessJson(null);
    }


    @RequestMapping(value = "/findFactory")
    @ResponseBody
    public Object findFactory(@RequestBody FactoryVO factoryVO)throws Exception{
        List<FactoryEntity> FactoryEntityList = factoryService.selectFactoryList(factoryVO);
        if(FactoryEntityList!=null){
            for(FactoryEntity factory:FactoryEntityList){
                Dictionary dictionaryArea = dictionaryService.selectDictionary(factory.getGoodAtArea());
                if(dictionaryArea != null){
                    factory.setGoodAtArea(dictionaryArea.getValue());// 擅长面积
                }


                String params = factory.getGoodAtIndustry();
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
                    factory.setGoodAtIndustry(temp);// 擅长行业
                }

                String material = factory.getGoodAtMaterial();
                if (!StringUtils.isEmpty(material)){
                    String[] paramsList = material.split(",");
                    String temp = "";
                    for(int i = 0 ; i < paramsList.length; i++){
                        Dictionary dictionary = dictionaryService.selectDictionary(paramsList[i]);
                        if(i != paramsList.length -1){
                            temp = temp + dictionary.getValue()  +",";
                        }else{
                            temp = temp + dictionary.getValue();
                        }
                    }
                    factory.setGoodAtMaterial(temp);// 擅长材料
                }
            }
        }
        int count = factoryService.countFactoryList(factoryVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", factoryVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", FactoryEntityList);
        return map;
    }
}
