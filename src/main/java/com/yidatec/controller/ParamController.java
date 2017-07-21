package com.yidatec.controller;

import com.yidatec.mapper.RoleMapper;
import com.yidatec.model.Param;
import com.yidatec.model.Role;
import com.yidatec.service.ParamService;
import com.yidatec.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jrw on 2017/7/21.
 */
@Controller
public class ParamController extends BaseController{

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    ParamService paramService;

    @RequestMapping("/paramList")
    public String paramList(ModelMap model){
        List<Role> roleList = roleMapper.findEnable();
        model.put("roleList",roleList);
        return "paramList";
    }

    @RequestMapping("/paramSelectInit")
    @ResponseBody
    public Object paramSelectInit(ModelMap model){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("vendorAppoiment",paramService.findParam(Constants.VENDORAPPOINTMENT_PARAM_ID));
        return map;
    }


    @RequestMapping(value = "/saveParam")
    @ResponseBody
    public Object saveParam(@RequestBody Param param){
        if(param.getParamList() !=null && param.getParamList().size() > 0){
            for (int i = 0; i < param.getParamList().size(); i++){
                Param param1 = param.getParamList().get(i);
                paramService.create(param1);
            }
        }
        return getSuccessJson(null);
    }
}
