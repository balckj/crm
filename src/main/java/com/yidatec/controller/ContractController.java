package com.yidatec.controller;

import com.yidatec.service.DictionaryService;
import com.yidatec.util.Constants;
import com.yidatec.vo.LedgerVO;
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

import java.util.HashMap;
import java.util.List;

/**
 * Created by jrw on 2017/7/13.
 */
@Controller
public class ContractController extends BaseController{

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping("/contractList")
    public String contractList(){
        return "contractList";
    }

    @RequestMapping("/contractEdit")
    public String contractEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建合同":"编辑合同");
        return "contractEdit";
    }

    /**
     * 初始化时加载台账的各种list
     * @return
     */
    @RequestMapping("/ledgerInitList")
    @ResponseBody
    public Object ledgerInitList(){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("moneyTypeList",dictionaryService.selectDictionaryListByCodeCommon(Constants.MONEY_TYPE)); // 款项类型
        map.put("paymentMethodList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PAYMENT_METHOD)); // 支付方式
        map.put("costCenterList",dictionaryService.selectDictionaryListByCodeCommon(Constants.COST_CENTER)); // 成本中心
        return map;
    }

    @RequestMapping("/checkLedgerInfo")
    @ResponseBody
    public Object checkLedgerInfo(@Validated @RequestBody LedgerVO ledger,
                                  BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }

        return getSuccessJson(null);
    }
}
