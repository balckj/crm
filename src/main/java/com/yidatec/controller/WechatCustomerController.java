package com.yidatec.controller;

import com.yidatec.model.Customer;
import com.yidatec.model.Dictionary;
import com.yidatec.service.CustomerService;
import com.yidatec.service.DictionaryService;
import com.yidatec.util.Constants;
import com.yidatec.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Qu on 2017/8/9.
 */
@CrossOrigin(origins = "*")
@RestController
public class WechatCustomerController extends BaseController{

    @Autowired
    CustomerService customerService;

    @Autowired
    DictionaryService dictionaryService;



    @RequestMapping(value="/customer/{customerId}",method=RequestMethod.GET)
    public Map<String,Object> customerEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
//        model.put("title",(id == null || id.isEmpty())?"新建客户":"编辑客户");
//        model.put("customer",customerService.selectCustomer(id));
//        model.put("industryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_INDUSTRY_CODE));// 所属行业
//        model.put("natureList",dictionaryService.selectDictionaryListByCodeCommon(Constants.NATURE_CODE));// 企业性质
//        model.put("levelList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PLATFORM_LEVEL));// 平台等级
//        return "customerEdit";
        return null;
    }

    @RequestMapping(value = "/customers",method=RequestMethod.POST)
    public Object findCustomer(@RequestBody CustomerVO customerVO)throws Exception{
        List<Customer> CustomerEntityList = customerService.selectCustomerList(customerVO);
        if (CustomerEntityList!=null){
            for (Customer customer:CustomerEntityList){
                Dictionary dictionaryIndustry = dictionaryService.selectDictionary(customer.getIndustry());
                if(dictionaryIndustry != null){
                    customer.setIndustry(dictionaryIndustry.getValue());// 所属行业
                }

                Dictionary dictionaryNature = dictionaryService.selectDictionary(customer.getNature());
                if(dictionaryNature != null){
                    customer.setNature(dictionaryNature.getValue());// 企业性质
                }
            }
        }
        int count = customerService.countCustomerList(customerVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", customerVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", CustomerEntityList);
        return map;
    }


//    @RequestMapping("/saveCustomer")
//    @ResponseBody
//    public Object saveCustomer(@Validated @RequestBody Customer customer,
//                                 BindingResult result)throws Exception{
//        List<FieldError> errors = result.getFieldErrors();
//        if(errors  != null && errors.size() > 0){
//            return errors;
//        }
//        CustomerVO customer1 = new CustomerVO();
////        Contact contact = new Contact();
//        if(customer.getId() == null || customer.getId().trim().length() <= 0)//新建
//        {
//            customer.setId(UUID.randomUUID().toString());
//            customer.setCreatorId(getWebUser().getId());
//            customer.setCreateTime(LocalDateTime.now());
//            customer.setModifierId(getWebUser().getCreatorId());
//            customer.setModifyTime(customer.getCreateTime());
//            customerService.createCustomer(customer);
//
//        } else {//编辑
//            customer.setModifierId(getWebUser().getId());
//            customer.setModifyTime(LocalDateTime.now());
//            customerService.updateCustomer(customer);
//        }
//        return getSuccessJson(null);
//    }
//
//    @RequestMapping("/getContact")
//    @ResponseBody
//    public Object getContact(@Validated @RequestBody String id){
//        customerService.getContact(id);
//        return getSuccessJson(null);
//    }

}
