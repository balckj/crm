package com.yidatec.service;

import com.yidatec.mapper.ActivityMapper;
import com.yidatec.model.Activity;
import com.yidatec.vo.ActivityVO;
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

    public Activity selectActivity(String id){
        return  activityMapper.selectActivity(id);
    }
    /**
     * 查询客户数据
     *
     * @return
     */
    public List<Activity> selectActivityList(ActivityVO activityVO) {
        return activityMapper.selectActivityList(activityVO);
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
