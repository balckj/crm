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
        // 供应商空闲时间
        map.put("vendorAppoiment",paramService.findParam(Constants.VENDORAPPOINTMENT_PARAM_ID));
        // 设计师
        map.put("designer",paramService.findParam(Constants.DESIGNER_PARAM_ID));
        // 项目经理
        map.put("pm",paramService.findParam(Constants.PM_PARAM_ID));
        // 销售
        map.put("sale",paramService.findParam(Constants.SALE_PARAM_ID));
        return map;
    }


    @RequestMapping(value = "/saveParam")
    @ResponseBody
    public Object saveParam(@RequestBody Param param){
        paramService.create(param);
        return getSuccessJson(null);
    }
}
