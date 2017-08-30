package com.yidatec.controller;

import com.yidatec.mapper.ParamMapper;
import com.yidatec.model.Dictionary;
import com.yidatec.model.Param;
import com.yidatec.model.User;
import com.yidatec.service.AvailableSupplierService;
import com.yidatec.service.DictionaryService;
import com.yidatec.util.Constants;
import com.yidatec.vo.AvailableSupplierVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jrw on 2017/7/11.
 * 可用供应商列表Controller
 */
@Controller
public class AvailableSuppliersListController extends BaseController{

    @Autowired
    AvailableSupplierService availableSupplierService;
    @Autowired
    ParamMapper paramMapper;
    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping("/availableSuppliersIndex")
    public String availableSuppliersIndex(){
        return "AvailableSuppliersList";
    }

    @RequestMapping("/availableSuppliersList")
    public String availableSuppliersList(ModelMap model){
        return "availableSuppliersList";
    }

    @RequestMapping("/availableSuppliersEdit")
    public String availableSuppliersEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        return "availableSuppliersEdit";
    }

    @RequestMapping("/saveAvailableSuppliers")
    @ResponseBody
    public Object saveAvailableSuppliers(@Validated @RequestBody User userParam,
                            BindingResult result)throws Exception{
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findAvailableSuppliers")
    @ResponseBody
    public Object findAvailableSuppliers(@RequestBody AvailableSupplierVO availableSupplierVO)throws Exception{
        Param param = paramMapper.findParamById(Constants.VENDORAPPOINTMENT_PARAM_ID);
        List<Param> paramList = new ArrayList<>();
        paramList.add(param);
        List<AvailableSupplierVO> availableSupplierVOList = availableSupplierService.selectAvailableSupplierList(availableSupplierVO,paramList);

        if (availableSupplierVOList != null) {
           for (AvailableSupplierVO a:  availableSupplierVOList){
               if (!StringUtils.isEmpty(a.getPlatformLevel())) {
                    Dictionary dictionary = dictionaryService.selectDictionary(a.getPlatformLevel());
                    a.setPlatformLevel(dictionary != null ?dictionary.getValue():"");
               }
           }
        }
        int count = availableSupplierService.countSelectAvailableSupplierList(availableSupplierVO,paramList);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", availableSupplierVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", availableSupplierVOList);
        return map;
    }
}
