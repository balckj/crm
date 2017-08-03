package com.yidatec.controller;

import com.yidatec.model.*;
import com.yidatec.service.ParamService;
import com.yidatec.service.SaleService;
import com.yidatec.util.Constants;
import com.yidatec.vo.SaleVO;
import com.yidatec.vo.UserVO;
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
 * Created by jrw on 2017/7/10.
 */
@Controller
public class SalesController extends BaseController{
    @Autowired
    SaleService saleService;

    /**
     * 转到字典主页
     * @return
     */
    @RequestMapping(value = "saleList")
    public String saleList(ModelMap model) {
        return "saleList";
    }

    @RequestMapping("/saleEdit")
    public String saleEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建销售":"编辑销售");
        model.put("sale",saleService.selectSale(id));
        return "saleEdit";
    }

    @RequestMapping("/saveSale")
    @ResponseBody
    public Object saveSale(@Validated({UserValidateSale.class}) @RequestBody User sale,
                                 BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        User sale1 = new User();
        if(sale.getId() == null || sale.getId().trim().length() <= 0)//新建
        {
            sale1.setId(UUID.randomUUID().toString().toLowerCase());
            sale1.setName(sale.getName());
            sale1.setChannel(sale.getChannel());
            sale1.setEmail(sale.getEmail());
            sale1.setMobilePhone(sale.getMobilePhone());
            sale1.setPassword(sale.getPassword());
            sale1.setState(sale.getState());
            sale1.setCreatorId(getWebUser().getId());
            sale1.setCreateTime(LocalDateTime.now());
            sale1.setModifierId(getWebUser().getId());
            sale1.setModifyTime(sale1.getCreateTime());
            saleService.createSale(sale1);
        } else {
            sale1.setId(sale.getId());
            sale1.setName(sale.getName());
            sale1.setChannel(sale.getChannel());
            sale1.setEmail(sale.getEmail());
            sale1.setMobilePhone(sale.getMobilePhone());
            sale1.setPassword(sale.getPassword());
            sale1.setState(sale.getState());
            sale1.setModifierId(getWebUser().getId());
            sale1.setModifyTime(LocalDateTime.now());
            saleService.updateSale(sale1);
        }
        return getSuccessJson(null);
    }


    @RequestMapping(value = "/findSale")
    @ResponseBody
    public Object findsale(@RequestBody UserVO userVO)throws Exception{
        List<User> saleEntityList = saleService.selectSaleListByName(userVO);
        int count = saleService.countSaleListByName(userVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", userVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", saleEntityList);
        return map;
    }
}
