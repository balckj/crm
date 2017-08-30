package com.yidatec.controller;

import com.yidatec.model.Customer;
import com.yidatec.model.Dictionary;
import com.yidatec.service.CustomerService;
import com.yidatec.service.DictionaryService;
import com.yidatec.service.WechatService;
import com.yidatec.util.ConfProperties;
import com.yidatec.util.Constants;
import com.yidatec.util.WeixinHelper;
import com.yidatec.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
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

public class WechatJsSdkSignatureController extends BaseController{

    @Autowired
    WechatService wechatService;

    @Autowired
    ConfProperties confProperties;


    @RequestMapping("/getJsSdkSignature")
    public Object getContact(@RequestParam(value="other") String url){
        Map<String,String> signure = wechatService.generateJSAPISignature(confProperties.getWeChatHost()+confProperties.getWeChatContextPath()+url);
        return signure;
    }
}
