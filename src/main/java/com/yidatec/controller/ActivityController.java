package com.yidatec.controller;

import com.yidatec.model.Activity;
import com.yidatec.service.ActivityService;
import com.yidatec.service.CustomerService;
import com.yidatec.service.DictionaryService;
import com.yidatec.service.ExhibitionService;
import com.yidatec.util.Constants;
import com.yidatec.vo.ActivityVO;
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
 * Created by Administrator on 2017/7/21.
 * @author xingfei
 */
@Controller
public class ActivityController extends BaseController{
    @Autowired
    ActivityService activityService;

    @Autowired
    ExhibitionService exhibitionService;

    @Autowired
    CustomerService customerService;

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping("/activityList")
    public String activities(ModelMap model){
        model.put("type",dictionaryService.selectDictionaryListByCodeCommon(Constants.ACTIVITY_TYPE));
        return "activityList";
    }

    @RequestMapping("/activityEdit")
    public String activityEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建活动":"编辑活动");
        model.put("type",dictionaryService.selectDictionaryListByCodeCommon(Constants.ACTIVITY_TYPE));
        model.put("activity",activityService.selectActivity(id));
        model.put("exhibitioHallList",exhibitionService.selectExhibitionAll());/*展馆列表*/
        model.put("customerList",customerService.selectCustomerAll());/*主办方列表*/
        return "activityEdit";
    }

    @RequestMapping("/saveActivity")
    @ResponseBody
    public Object saveActivity(@Validated @RequestBody Activity activity,
                               BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        if(activity.getId() == null || activity.getId().trim().length() <= 0)/*新建*/
        {
            activity.setId(UUID.randomUUID().toString().toLowerCase());
            activity.setCreatorId(getWebUser().getId());
            activity.setCreateTime(LocalDateTime.now());
            activity.setModifierId(activity.getCreatorId());
            activity.setModifyTime(activity.getModifyTime());
            activityService.createActivity(activity);
        } else {/*编辑*/
            activity.setModifierId(getWebUser().getId());
            activity.setModifyTime(LocalDateTime.now());
            activityService.updateActivity(activity);
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findActivity")
    @ResponseBody
    public Object findActivity(@RequestBody ActivityVO activityVO)throws Exception{
        List<Activity> activityEntityList = activityService.selectActivityList(activityVO);
        int count = activityService.countActivityList(activityVO);
        Map<String, Object> map = new HashMap<String, Object>(4);
        map.put("draw", activityVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", activityEntityList);
        return map;
    }

}
