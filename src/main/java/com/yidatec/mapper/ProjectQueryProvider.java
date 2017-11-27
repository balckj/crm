package com.yidatec.mapper;

import com.yidatec.vo.ProjectVO;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/8/17.
 */
public class ProjectQueryProvider {

    public String selectProject(final ProjectVO projectVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT b.name as customerName,c.name as campaignName,c.startDate,c.endDate,D.* FROM T_PROJECT  as D LEFT JOIN T_CUSTOMER b " +
                "on D.customerId=b.id " +
                "LEFT JOIN T_CAMPAIGN c " +
                "on D.campaignId = c.id WHERE 1=1");

        if(!StringUtils.isEmpty(projectVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(projectVO.getCampaignId())){
            sb.append(" AND c.name LIKE CONCAT('%',#{campaignId},'%')");
        }
        if(!StringUtils.isEmpty(projectVO.getActivityTime())){
            sb.append(" AND (Date(#{activityTime}) between Date(c.startDate) and  Date(c.endDate))");
        }
        if(!StringUtils.isEmpty(projectVO.getCode())){
            sb.append(" AND D.code LIKE CONCAT('%',#{code},'%')");
        }
        if(!StringUtils.isEmpty(projectVO.getDegreeOfImportance())){
            sb.append(" AND D.degreeOfImportance = #{degreeOfImportance}");
        }
        if(!StringUtils.isEmpty(projectVO.getPmId())){
            sb.append(" AND D.pmId = #{pmId}");
        }

        if(!StringUtils.isEmpty(projectVO.getState())){
            sb.append(" AND D.state = #{state}");
        }
        //
        if(!StringUtils.isEmpty(projectVO.getSearch())){
            sb.append(" AND D.name LIKE CONCAT('%',#{search},'%') OR D.code LIKE CONCAT('%',#{search},'%') OR c.name LIKE CONCAT('%',#{search},'%')");
        }
        if(!projectVO.isAdmin()){
            sb.append(" AND (");
            sb.append(" D.pmId = #{id}");
            sb.append(" or D.developSaleId = #{id}");
            sb.append(" or D.traceSaleId = #{id}");
            sb.append(" OR #{id} in (SELECT pd.designerId FROM T_PROJECT p LEFT JOIN T_PROJECT_DESIGNER pd on p.id = pd.projectid where D.id = p.id)");
            sb.append(" )");
        }
        sb.append(" ORDER BY D.modifyTime DESC LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countProject(final ProjectVO projectVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) FROM T_PROJECT  as D LEFT JOIN T_CUSTOMER b\n" +
                "on D.customerId=b.id\n" +
                "LEFT JOIN T_CAMPAIGN c\n" +
                "on D.campaignId = c.id WHERE 1=1");

        if(!StringUtils.isEmpty(projectVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(projectVO.getCampaignId())){
            sb.append(" AND c.name LIKE CONCAT('%',#{campaignId},'%')");
        }
        if(!StringUtils.isEmpty(projectVO.getCode())){
            sb.append(" AND D.code LIKE CONCAT('%',#{code},'%')");
        }
        if(!StringUtils.isEmpty(projectVO.getDegreeOfImportance())){
            sb.append(" AND D.degreeOfImportance = #{degreeOfImportance}");
        }
        if(!StringUtils.isEmpty(projectVO.getPmId())){
            sb.append(" AND D.pmId = #{pmId}");
        }
        if(!projectVO.isAdmin()){
            sb.append(" AND (");
            sb.append(" D.pmId = #{id}");
            sb.append(" or D.developSaleId = #{id}");
            sb.append(" or D.traceSaleId = #{id}");
            sb.append(" OR #{id} in (SELECT pd.designerId FROM T_PROJECT p LEFT JOIN T_PROJECT_DESIGNER pd on p.id = pd.projectid where D.id = p.id)");
            sb.append(" )");
        }
        if(!StringUtils.isEmpty(projectVO.getState())){
            sb.append(" AND D.state = #{state}");
        }
        if(!StringUtils.isEmpty(projectVO.getSearch())){
            sb.append(" AND D.name LIKE CONCAT('%',#{search},'%') OR D.code LIKE CONCAT('%',#{search},'%') OR c.name LIKE CONCAT('%',#{search},'%')");
        }
        return sb.toString();
    }
}
