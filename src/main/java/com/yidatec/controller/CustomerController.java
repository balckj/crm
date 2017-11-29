package com.yidatec.controller;

import com.yidatec.model.Customer;
import com.yidatec.model.Dictionary;
import com.yidatec.model.FollowHistoryVO;
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

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping("/customerList")
    public String customerList(ModelMap model){
        model.put("industryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_INDUSTRY_CODE));// 所属行业
        model.put("nature",dictionaryService.selectDictionaryListByCodeCommon(Constants.NATURE_CODE));
        model.put("isAll",0);
        return "customerList";
    }

    @RequestMapping("/customerListAll")
    public String customerListAll(ModelMap model){
        model.put("industryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_INDUSTRY_CODE));// 所属行业
        model.put("nature",dictionaryService.selectDictionaryListByCodeCommon(Constants.NATURE_CODE));
        model.put("isAll",1);
        return "customerList";
    }


    @RequestMapping(value={"/customerEdit","/customerCreate","/customerEditAll","/customerCreateAll"})
    public String customerEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建客户":"编辑客户");
        model.put("customer",customerService.selectCustomer(id));
        model.put("source",dictionaryService.selectDictionaryListByCodeCommon(Constants.SOURCE));// 客户来源
        model.put("position",dictionaryService.selectDictionaryListByCodeCommon(Constants.POSITION));// 职位
        model.put("industryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_INDUSTRY_CODE));// 所属行业
        model.put("natureList",dictionaryService.selectDictionaryListByCodeCommon(Constants.NATURE_CODE));// 企业性质
        model.put("levelList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PLATFORM_LEVEL));// 平台等级
        return "customerEdit";
    }

    @RequestMapping(value = "/findCustomer")
    @ResponseBody
    public Object findCustomer(@RequestBody CustomerVO customerVO)throws Exception{
        customerVO.setCreatorId(getWebUser().getId());
//        customerVO.setAdmin(isAdmin());
        List<CustomerVO> CustomerEntityList = customerService.selectCustomerList(customerVO);
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


    @RequestMapping("/saveCustomer")
    @ResponseBody
    public Object saveCustomer(@Validated @RequestBody Customer customer,
                                 BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        CustomerVO customer1 = new CustomerVO();
//        Contact contact = new Contact();
        if(customer.getId() == null || customer.getId().trim().length() <= 0)//新建
        {
            customer.setId(UUID.randomUUID().toString());
            customer.setCreatorId(getWebUser().getId());
            customer.setCreateTime(LocalDateTime.now());
            customer.setModifierId(getWebUser().getCreatorId());
            customer.setModifyTime(customer.getCreateTime());
            customerService.createCustomer(customer);

        } else {//编辑
            customer.setModifierId(getWebUser().getId());
            customer.setModifyTime(LocalDateTime.now());
            customerService.updateCustomer(customer);
        }
        return getSuccessJson(null);
    }

    @RequestMapping("/getContact")
    @ResponseBody
    public Object getContact(@Validated @RequestBody String id){
        customerService.getContact(id);
        return getSuccessJson(null);
    }

    /**
     * 跟进历史
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value={"/historyEdit","/historyEditAll"})
    public String historyEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
//        model.put("contract",contractMapper.selectContract(id));
        model.put("historyList",customerService.getHistoryList(id));
        model.put("historyListSize",
                customerService.getHistoryList(id) != null && customerService.getHistoryList(id).size() > 0
                        ? customerService.getHistoryList(id).size(): 0);
        model.put("historyListFlg",customerService.getHistoryList(id) != null && customerService.getHistoryList(id).size() > 0 ? true :false);
        return "customerHistoryList";// 返回跟进历史html
    }

    /**
     * 跟进历史
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveHistory")
    @ResponseBody
    public Object checkLedgerInfo(@Validated @RequestBody FollowHistoryVO history,
                                  BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }

        //新建 更新。此id实际上是合同的id，台账依托于合同，所以这里一定个更新没有新建
        if(history.getId() != null || history.getId().trim().length() >= 0) {
            history.setCreatorId(getWebUser().getId());
            history.setCreateTime(LocalDateTime.now());
            history.setModifierId(getWebUser().getCreatorId());
            history.setModifyTime(history.getCreateTime());
            customerService.createHistory(history);
        }
        return getSuccessJson(null);
    }

}
