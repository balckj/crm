package com.yidatec.mapper;


import com.yidatec.model.Activity;
import com.yidatec.vo.ActivityVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
@Mapper
public interface ActivityMapper {

    /**
     * 载入客户可列表
     * @return
     */
    @Select("SELECT * FROM T_CAMPAIGN WHERE id = #{id}")
    Activity selectActivity(String id);

    @Select("SELECT * FROM T_CAMPAIGN")
    List<Activity> activityList();

    @SelectProvider(type=com.yidatec.mapper.ActivityQueryProvider.class,method = "selectActivity")
    List<Activity> selectActivityList(ActivityVO activityVO);

    @SelectProvider(type=com.yidatec.mapper.ActivityQueryProvider.class,method = "countActivity")
    int countActivityList(ActivityVO activityVo);

    @Insert("INSERT INTO T_CAMPAIGN (id,name,startDate,endDate,country,province,city,region,address,state,exhibitioHall,sponsor,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{name},#{startDate}," +
            "#{endDate},#{country},#{province},#{city},#{region},#{address},#{state},#{exhibitioHall},#{sponsor},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int create(Activity activity);

    @Update("UPDATE T_CAMPAIGN SET `name`=#{name},startDate=#{startDate},endDate=#{endDate}," +
            "country=#{country},province=#{province},city=#{city},region=#{region},address=#{address},state=#{state},exhibitioHall=#{exhibitioHall},sponsor=#{sponsor}," +
            "modifierId=#{modifierId}," +
            "modifyTime=#{modifyTime} WHERE id=#{id}")
    int update(Activity activity);
}
