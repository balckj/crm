package com.yidatec.service;

import com.yidatec.mapper.ActivityMapper;
import com.yidatec.mapper.CustomerMapper;
import com.yidatec.model.Activity;
import com.yidatec.model.Customer;
import com.yidatec.vo.ActivityVO;
import com.yidatec.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
@Service("activityService")
public class ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    CustomerMapper customerMapper;

    public ActivityVO selectActivity(String id){
        ActivityVO activity = activityMapper.selectActivity(id);
        if(activity!=null){
            activity.setSponsorName("".equals(activity.getSponsor())?"":customerMapper.selectCustomer(activity.getSponsor()).getName());
            activity.setOrganizerName("".equals(activity.getOrganizer())?"":customerMapper.selectCustomer(activity.getOrganizer()).getName());
            activity.setBuilderName("".equals(activity.getBuilder())?"":customerMapper.selectCustomer(activity.getBuilder()).getName());
        }
        return  activity;
    }
    /**
     * 查询客户数据
     *
     * @return
     */
    public List<Activity> selectActivityList(ActivityVO activityVO) {
        return activityMapper.selectActivityList(activityVO);
    }

    /**
     * 查询所有活动
     * @return
     */
    public List<Activity> activityList() {
        return activityMapper.activityList();
    }

    public int countActivityList(ActivityVO activityVO) {
        return activityMapper.countActivityList(activityVO);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void createActivity(Activity activity) {
        activityMapper.create(activity);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void updateActivity(Activity activity) {
        activityMapper.update(activity);
    }


}
