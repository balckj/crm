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
        sb.append("SELECT b.name as customerName,c.name as exhibitioHallName,u.value as type,D.* FROM T_CAMPAIGN  as D LEFT JOIN T_CUSTOMER b on D.sponsor = b.id LEFT JOIN T_EXHIBITION_HALL c on D.exhibitioHall=c.id LEFT JOIN T_DICTIONARY u ON D.type = u.id  WHERE 1=1 ");

        if(!StringUtils.isEmpty(activityVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(activityVO.getProvince())){
            sb.append(" AND D.province LIKE CONCAT('%',#{province},'%')");
        }
        if(!StringUtils.isEmpty(activityVO.getCity())){
            sb.append(" AND D.city LIKE CONCAT('%',#{city},'%')");
        }
        if(!StringUtils.isEmpty(activityVO.getType())){
            sb.append(" AND D.type = #{type}");
        }
        if(!StringUtils.isEmpty(activityVO.getAddress())){
            sb.append(" AND D.address LIKE CONCAT('%',#{address},'%')");
        }
        if(!StringUtils.isEmpty(activityVO.getStartDate())&&!StringUtils.isEmpty(activityVO.getEndDate())){
            sb.append(" AND (Date(D.startDate) between Date(#{startDate}) and  Date(#{endDate}))");
        }
        if(!StringUtils.isEmpty(activityVO.getCreatorId())){
            sb.append(" AND D.creatorId = #{creatorId}");
        }
        if(!StringUtils.isEmpty(activityVO.getState())){
            sb.append(" AND D.state = #{state}");
        }
        sb.append(" ORDER BY D.modifyTime DESC");
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
        if(!StringUtils.isEmpty(activityVO.getProvince())){
            sb.append(" AND D.province LIKE CONCAT('%',#{province},'%')");
        }
        if(!StringUtils.isEmpty(activityVO.getCity())){
            sb.append(" AND D.city LIKE CONCAT('%',#{city},'%')");
        }
        if(!StringUtils.isEmpty(activityVO.getType())){
            sb.append(" AND D.type = #{type}");
        }
        if(!StringUtils.isEmpty(activityVO.getAddress())){
            sb.append("  AND D.address LIKE CONCAT('%',#{address},'%')");
        }
        if(!StringUtils.isEmpty(activityVO.getStartDate())&&!StringUtils.isEmpty(activityVO.getEndDate())){
            sb.append(" AND (Date(D.startDate) between Date(#{startDate}) and  Date(#{endDate}))");
        }
        if(!StringUtils.isEmpty(activityVO.getCreatorId())){
            sb.append(" AND D.creatorId = #{creatorId}");
        }
        if(!StringUtils.isEmpty(activityVO.getState())){
            sb.append(" AND D.state = #{state}");
        }
        return sb.toString();
    }
}
