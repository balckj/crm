package com.yidatec.controller;

import com.yidatec.exception.BusinessException;
import com.yidatec.exception.ExceptionID;
import com.yidatec.model.Activity;
import com.yidatec.model.Customer;
import com.yidatec.model.Dictionary;
import com.yidatec.service.AccessTokenService;
import com.yidatec.service.CustomerService;
import com.yidatec.service.DictionaryService;
import com.yidatec.service.WechatService;
import com.yidatec.util.ConfProperties;
import com.yidatec.util.Constants;
import com.yidatec.util.FileUtills;
import com.yidatec.util.HttpClientHelper;
import com.yidatec.vo.CustomerVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
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
    protected static final Log logger = LogFactory
            .getLog(WechatCustomerController.class);
    @Autowired
    CustomerService customerService;

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    WechatService wechatService;

    @Autowired
    ConfProperties confProperties;

    @Autowired
    AccessTokenService accessTokenService;



    @RequestMapping(value={"/customer","/customer/{customerId}"},method=RequestMethod.GET)
    public Object customerEdit(ModelMap model,@RequestParam(value="id",required = false) String id,@RequestParam(value="other") String url){


        Map<String,Object> res;
        if(id == null){//新建
            res = new HashMap<String,Object>();
            res.put("res",1);
            res.put("industryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_INDUSTRY_CODE));// 所属行业
            res.put("natureList",dictionaryService.selectDictionaryListByCodeCommon(Constants.NATURE_CODE));// 企业性质
            res.put("levelList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PLATFORM_LEVEL));// 平台等级

        }else{//编辑
            Customer customer = customerService.selectCustomer(id);
            if(customer == null)
                return getErrorJson("客户不存在！");
            else{
                res = new HashMap<String,Object>();
                res.put("res",1);
                res.put("customer",customerService.selectCustomer(id));
                res.put("industryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.GOOD_AT_INDUSTRY_CODE));// 所属行业
                res.put("natureList",dictionaryService.selectDictionaryListByCodeCommon(Constants.NATURE_CODE));// 企业性质
                res.put("levelList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PLATFORM_LEVEL));// 平台等级
            }
        }
        Map<String,String> signure = wechatService.generateJSAPISignature(confProperties.getWeChatHost()+confProperties.getWeChatContextPath()+url);
        res.putAll(signure);
        return res;
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


    @RequestMapping(value = "/customers",method=RequestMethod.PUT)
    @ResponseBody
    public Object saveCustomer(@Validated @RequestBody Customer customer,
                                 BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        String mediaIds = customer.getMediaIds();
        String images = "";
        if(mediaIds != null && !mediaIds.trim().isEmpty()){
            String[] mediaArr = mediaIds.split(",");
            if(mediaArr != null && mediaArr.length>0){

                for(String one : mediaArr){
                    if(one != null && !one.trim().isEmpty()){
                        String url="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+accessTokenService.getAccessToken()+"&media_id="+one.trim();
                        FileOutputStream fos = null;
                        try {
                            byte[] imageData = HttpClientHelper.downloadFile(url);
                            if (imageData != null && imageData.length > 0) {
                                String tmp = UUID.randomUUID().toString()+".jpg";
                                images+=tmp+",";
                                String path = FileUtills.buildFile(confProperties.getImagePath(), true) + File.separator + tmp;

                                fos = new FileOutputStream(path);
                                fos.write(imageData);
                                fos.flush();
                            }
                        }catch(Exception ex){
                            logger.error(ex);
                            throw new BusinessException(ExceptionID.DOWNLOAD_FILE_ERROR);
                        }finally {
                            if(fos != null){
                                try{
                                    fos.close();
                                }catch(Exception ex){}
                            }
                        }
                    }
                }
            }
        }
        if(images.length()>0){
            images = images.substring(0, images.length()-1);
            customer.setPhoto(images);
        }
        if(customer.getId() == null || customer.getId().trim().length() <= 0)//新建
        {
            customer.setId(UUID.randomUUID().toString());
//            customer.setCreatorId(getWebUser().getId());
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

    @RequestMapping("/getCustomerContact")
    public Object getContact(@Validated @RequestBody String id){
        customerService.getContact(id);
        return getSuccessJson(null);
    }
}
