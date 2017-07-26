package com.yidatec.controller;

import com.yidatec.model.Contact;
import com.yidatec.model.Customer;
import com.yidatec.service.CustomerService;
import com.yidatec.vo.CustomerVO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/11.
 */
@Controller
public class CustomerController extends BaseController{

    @Autowired
    CustomerService customerService;

    @RequestMapping("/customerList")
    public String customerList(ModelMap model){
        return "customerList";
    }

//    @RequestMapping("/activities")
//    public String activities(ModelMap model){
//        return "activities";
//    }


    @RequestMapping("/customerEdit")
    public String customerEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建客户":"编辑客户");
        model.put("customer",customerService.selectCustomer(id));
        return "customerEdit";
    }

//    @RequestMapping("/activityEdit")
//    public String activityEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
//        model.put("title",(id == null || id.isEmpty())?"新建活动":"编辑活动");
//
//        return "activityEdit";
//    }

    @RequestMapping(value = "/findCustomer")
    @ResponseBody
    public Object findCustomer(@RequestBody CustomerVO customerVO)throws Exception{
        List<Customer> CustomerEntityList = customerService.selectCustomerList(customerVO);
        int count = customerService.countDictionaryList(customerVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", customerVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", CustomerEntityList);
        return map;
    }


    @RequestMapping("/saveCustomer")
    @ResponseBody
    public Object saveCustomer(@Validated @RequestBody CustomerVO customer,
                                 BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        CustomerVO customer1 = new CustomerVO();
        Contact contact = new Contact();
        if(customer.getId() == null || customer.getId().trim().length() <= 0)//新建
        {
            customer1.setId(UUID.randomUUID().toString().toLowerCase());
            customer1.setCompanyName(customer.getCompanyName());
            customer1.setCompanyId(customer.getCompanyId());
            customer1.setIndustry(customer.getIndustry());
            customer1.setNature(customer.getNature());
            customer1.setCountry(customer.getCountry());
            customer1.setProvince(customer.getProvince());
            customer1.setCity(customer.getCity());
            customer1.setRegion(customer.getRegion());
            customer1.setAddress(customer.getAddress());
            customer1.setLevel(customer.getLevel());
            customer1.setUserList(customer.getUserList());
            customer1.setState(customer.getState());
            customer1.setCreatorId(getWebUser().getId());
            customer1.setCreateTime(LocalDateTime.now());
            customer1.setModifierId(getWebUser().getCreatorId());
            customer1.setModifyTime(LocalDateTime.now());
            customerService.createCustomer(customer1,getWebUser());

        } else {//编辑
            customer1.setId(customer.getId());
            customer1.setCompanyName(customer.getCompanyName());
            customer1.setCompanyId(customer.getCompanyId());
            customer1.setIndustry(customer.getIndustry());
            customer1.setNature(customer.getNature());
            customer1.setCountry(customer.getCountry());
            customer1.setProvince(customer.getProvince());
            customer1.setCity(customer.getCity());
            customer1.setRegion(customer.getRegion());
            customer1.setAddress(customer.getAddress());
            customer1.setLevel(customer.getLevel());
            customer1.setUserList(customer.getUserList());
            customer1.setState(customer.getState());
            customer1.setModifierId(getWebUser().getId());
            customer1.setModifyTime(LocalDateTime.now());
            customerService.updateCustomer(customer1,getWebUser());
        }
        return getSuccessJson(null);
    }

}
