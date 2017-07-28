package com.yidatec.controller;

import com.yidatec.model.Contact;
import com.yidatec.service.FactoryService;
import com.yidatec.vo.FactoryVO;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by Administrator on 2017/7/12.
 */
@Controller
public class FactoryController extends BaseController{

    @Autowired
    FactoryService factoryService;

    @RequestMapping("/factoryList")
    public String factoryList(ModelMap model){
        return "factoryList";
    }

    @RequestMapping("/factoryEdit")
    public String factoryEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建工厂":"编辑工厂");
        return "factoryEdit";
    }

    @RequestMapping("/saveFactory")
    @ResponseBody
    public Object saveFactory(@Validated @RequestBody FactoryVO factory,
                               BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        FactoryVO factory1 = new FactoryVO();
        Contact contact = new Contact();
        if(factory.getId() == null || factory.getId().trim().length() <= 0)//新建
        {
            factory1.setId(UUID.randomUUID().toString().toLowerCase());
            factory1.setReferrer(factory.getReferrer());
            factory1.setFactoryName(factory.getFactoryName());
            factory1.setDirector(factory.getDirector());
            factory1.setFirstOrderTime(factory.getFirstOrderTime());
            factory1.setContact(factory.getContact());
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
            factoryService.createFactory(factory1,getWebUser());

        } else {//编辑
            factory1.setId(factory.getId());
            factory1.setId(UUID.randomUUID().toString().toLowerCase());
            factory1.setReferrer(factory.getReferrer());
            factory1.setFactoryName(factory.getFactoryName());
            factory1.setDirector(factory.getDirector());
            factory1.setFirstOrderTime(factory.getFirstOrderTime());
            factory1.setContact(factory.getContact());
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
}
