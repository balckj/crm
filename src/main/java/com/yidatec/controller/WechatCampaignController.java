package com.yidatec.controller;

import com.yidatec.model.Activity;
import com.yidatec.model.Customer;
import com.yidatec.service.ActivityService;
import com.yidatec.service.CustomerService;
import com.yidatec.service.ExhibitionService;
import com.yidatec.vo.ActivityVO;
import com.yidatec.vo.ExhibitionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class WechatCampaignController extends BaseController{
    @Autowired
    ActivityService activityService;

    @Autowired
    ExhibitionService exhibitionService;

    @Autowired
    CustomerService customerService;


    @RequestMapping(value="/campaign/{campaignId}",method=RequestMethod.GET)
    public Map<String,Object> activityEdit(ModelMap model,@RequestParam(value="id",required = false) String id){
//        model.put("title",(id == null || id.isEmpty())?"新建活动":"编辑活动");
//        model.put("activity",activityService.selectActivity(id));
//        model.put("exhibitioHallList",exhibitionService.selectExhibitionAll());// 展馆列表
//        model.put("customerList",customerService.selectCustomerAll());// 主办方列表
//        return "activityEdit";
        return null;
    }

//    @RequestMapping("/saveActivity")
//    public Object saveActivity(@Validated @RequestBody Activity activity,
//                               BindingResult result)throws Exception{
//        List<FieldError> errors = result.getFieldErrors();
//        if(errors  != null && errors.size() > 0){
//            return errors;
//        }
//        Activity activity1 = new Activity();
//        if(activity.getId() == null || activity.getId().trim().length() <= 0)//新建
//        {
//            activity1.setId(UUID.randomUUID().toString().toLowerCase());
//            activity1.setName(activity.getName());
//            activity1.setStartDate(activity.getStartDate());
//            activity1.setEndDate(activity.getEndDate());
//            activity1.setCountry(activity.getCountry());
//            activity1.setProvince(activity.getProvince());
//            activity1.setCity(activity.getCity());
//            activity1.setRegion(activity.getRegion());
//            activity1.setAddress(activity.getAddress());
//            activity1.setState(activity.getState());
//            activity1.setExhibitioHall(activity.getExhibitioHall());
//            activity1.setSponsor(activity.getSponsor());
//            activity1.setCreatorId(getWebUser().getId());
//            activity1.setCreateTime(LocalDateTime.now());
//            activity1.setModifierId(getWebUser().getCreatorId());
//            activity1.setModifyTime(LocalDateTime.now());
//            activityService.createActivity(activity1);
//        } else {//编辑
//            activity1.setId(activity.getId());
//            activity1.setName(activity.getName());
//            activity1.setStartDate(activity.getStartDate());
//            activity1.setEndDate(activity.getEndDate());
//            activity1.setCountry(activity.getCountry());
//            activity1.setProvince(activity.getProvince());
//            activity1.setCity(activity.getCity());
//            activity1.setRegion(activity.getRegion());
//            activity1.setAddress(activity.getAddress());
//            activity1.setState(activity.getState());
//            activity1.setExhibitioHall(activity.getExhibitioHall());
//            activity1.setSponsor(activity.getSponsor());
//            activity1.setState(activity.getState());
//            activity1.setModifierId(getWebUser().getId());
//            activity1.setModifyTime(LocalDateTime.now());
//            activityService.updateActivity(activity1);
//        }
//        return getSuccessJson(null);
//    }

    @RequestMapping(value = "/campaigns",method=RequestMethod.POST)
    public Object findActivity(@RequestBody ActivityVO activityVO)throws Exception{
        List<Activity> ActivityEntityList = activityService.selectActivityList(activityVO);
        if (ActivityEntityList!=null){
            for(Activity activity:ActivityEntityList){
                ExhibitionVO exhibition = exhibitionService.selectExhibition(activity.getExhibitioHall());
                if(exhibition != null){
                    activity.setExhibitioHall(exhibition.getName());
                }
                Customer customer = customerService.selectCustomer(activity.getSponsor());
                if(customer != null){
                    activity.setSponsor(customer.getName());
                }
            }
        }
        int count = activityService.countActivityList(activityVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", activityVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", ActivityEntityList);
        return map;
    }
}
