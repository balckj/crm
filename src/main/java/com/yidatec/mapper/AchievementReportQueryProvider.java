package com.yidatec.mapper;

import com.yidatec.vo.ActivityVO;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/7/21.
 */
public class AchievementReportQueryProvider {

    public String selectAchievementReportVOList(final String starTime,String endTime)
    {
        StringBuffer sb = new StringBuffer();

        sb.append(" SELECT")
        .append(" p.id as projectId,")
        .append(" c.id as contractId,")
        .append(" c.`code`as contractCode,")
        .append(" p.`name` as projectName,")
        .append(" ca.name as campaignName,")
        .append(" CONCAT(ca.startDate,'~',ca.endDate) AS campaignStartEndTime,")
        .append(" p.exhibitionNumber as exhibitionNumber")
        .append(" FROM T_PROJECT p")
        .append(" LEFT JOIN T_CONTRACT c ON p.id = c.projectId")
        .append(" LEFT JOIN T_CAMPAIGN ca ON p.campaignId = ca.id")
        .append(" ORDER BY p.modifyTime DESC");
        return sb.toString();
    }

}
