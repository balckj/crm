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
        model.put("platformLevelList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PLATFORM_LEVEL)); // 平台等级
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
        Param param = new Param();
        List<Param> paramList = new ArrayList<>();

        List<AvailableSupplierVO> availableSupplierVOList = new ArrayList<AvailableSupplierVO>();
        int count = 0;
        if(StringUtils.isEmpty(availableSupplierVO.getAvailableSupplierType())){
            param = paramMapper.findParamById(Constants.VENDORAPPOINTMENT_PARAM_ID);
            paramList.add(param);
            availableSupplierVOList = availableSupplierService.selectAvailableSupplierList(availableSupplierVO,paramList);
            count = availableSupplierService.countSelectAvailableSupplierList(availableSupplierVO,paramList);
        }else if("F".equalsIgnoreCase(availableSupplierVO.getAvailableSupplierType())){
            availableSupplierVOList = availableSupplierService.selectAvailableSupplierListF(availableSupplierVO,paramList);
            count = availableSupplierService.countSelectAvailableSupplierListF(availableSupplierVO,paramList);
        }else if("D".equalsIgnoreCase(availableSupplierVO.getAvailableSupplierType())){
            param = paramMapper.findParamById(Constants.DESIGNER_PARAM_ID);
            paramList.add(param);
            availableSupplierVOList = availableSupplierService.selectAvailableSupplierListD(availableSupplierVO,paramList);
            count = availableSupplierService.countSelectAvailableSupplierListD(availableSupplierVO,paramList);
        }else if("P".equalsIgnoreCase(availableSupplierVO.getAvailableSupplierType())){
            param = paramMapper.findParamById(Constants.PM_PARAM_ID);
            paramList.add(param);
            availableSupplierVOList = availableSupplierService.selectAvailableSupplierListP(availableSupplierVO,paramList);
            count = availableSupplierService.countSelectAvailableSupplierListP(availableSupplierVO,paramList);
        }else if("DS".equalsIgnoreCase(availableSupplierVO.getAvailableSupplierType())){
            param = paramMapper.findParamById(Constants.SALE_PARAM_ID);
            paramList.add(param);
            availableSupplierVOList = availableSupplierService.selectAvailableSupplierListDS(availableSupplierVO,paramList);
            count = availableSupplierService.countSelectAvailableSupplierListDS(availableSupplierVO,paramList);
        }else if("FS".equalsIgnoreCase(availableSupplierVO.getAvailableSupplierType())){
            param = paramMapper.findParamById(Constants.SALE_PARAM_ID);
            paramList.add(param);
            availableSupplierVOList = availableSupplierService.selectAvailableSupplierListFS(availableSupplierVO,paramList);
            count = availableSupplierService.countSelectAvailableSupplierListFS(availableSupplierVO,paramList);
        }

        if (availableSupplierVOList != null) {
           for (AvailableSupplierVO a:  availableSupplierVOList){
               if (!StringUtils.isEmpty(a.getPlatformLevel())) {
                    Dictionary dictionary = dictionaryService.selectDictionary(a.getPlatformLevel());
                    a.setPlatformLevel(dictionary != null ?dictionary.getValue():"");
               }
           }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", availableSupplierVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", availableSupplierVOList);
        return map;
    }
}
