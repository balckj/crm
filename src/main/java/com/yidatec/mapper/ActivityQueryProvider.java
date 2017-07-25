package com.yidatec.mapper;

import com.yidatec.vo.ActivityVO;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/7/21.
 */
public class ActivityQueryProvider {
    public String selectActivity(final ActivityVO activityVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM T_CAMPAIGN  as D WHERE 1=1 ");

        if(!StringUtils.isEmpty(activityVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(activityVO.getAddress())){
            sb.append(" AND D.address LIKE CONCAT('%',#{address},'%')");
        }
        if(!StringUtils.isEmpty(activityVO.getStartDate())){
            sb.append(" AND D.startDate = #{startDate}");
        }
        if(!StringUtils.isEmpty(activityVO.getEndDate())){
            sb.append(" AND D.endDate = #{endDate}");
        }
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countActivity(final ActivityVO activityVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) from T_CAMPAIGN  as D WHERE 1=1 ");

        if(!StringUtils.isEmpty(activityVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(activityVO.getAddress())){
            sb.append("  AND D.address LIKE CONCAT('%',#{address},'%')");
        }
        if(!StringUtils.isEmpty(activityVO.getStartDate())){
            sb.append(" AND D.startDate = #{startDate}");
        }
        if(!StringUtils.isEmpty(activityVO.getEndDate())){
            sb.append(" AND D.endDate = #{endDate}");
        }
        return sb.toString();
    }
}
