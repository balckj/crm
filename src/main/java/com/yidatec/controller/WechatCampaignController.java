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


    /**
     * 新建或编辑
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value={"/campaign","/campaign/{campaignId}"},method=RequestMethod.GET)
    public Object activityEdit(ModelMap model,@PathVariable(value = "campaignId",required = false) String id){
        Map<String,Object> res;
        if(id == null){//新建
            res = new HashMap<String,Object>();
            res.put("res",1);
            res.put("exhibitioHallList",exhibitionService.selectExhibitionAll());
            res.put("customerList",customerService.selectCustomerAll());
        }else{//编辑
            Activity campaign = activityService.selectActivity(id);
            if(campaign == null)
                return getErrorJson("活动不存在！");
            else{
                res = new HashMap<String,Object>();
                res.put("res",1);
                res.put("exhibitioHallList",exhibitionService.selectExhibitionAll());
                res.put("customerList",customerService.selectCustomerAll());
                res.put("campaign",campaign);
            }
        }
        return res;
    }

    /**
     * 保存
     * @param activity
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/campaigns",method=RequestMethod.PUT)
    public Object saveActivity(@Validated @RequestBody Activity activity,
                               BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        if(activity.getId() == null || activity.getId().trim().length() <= 0)//新建
        {
            activity.setId(UUID.randomUUID().toString().toLowerCase());
            activity.setCreatorId(getWebUser().getId());
            activity.setCreateTime(LocalDateTime.now());
            activity.setModifierId(activity.getCreatorId());
            activity.setModifyTime(activity.getModifyTime());
            activityService.createActivity(activity);
        } else {//编辑

            activity.setModifierId(getWebUser().getId());
            activity.setModifyTime(LocalDateTime.now());
            activityService.updateActivity(activity);
        }
        return getSuccessJson(null);
    }

    /**
     * 翻页查询
     * @param activityVO
     * @return
     * @throws Exception
     */
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
