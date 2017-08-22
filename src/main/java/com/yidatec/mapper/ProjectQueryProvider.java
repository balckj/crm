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
        sb.append("SELECT b.name as customerName,c.name as campaignName,D.* FROM T_PROJECT  as D LEFT JOIN T_CUSTOMER b\n" +
                "on D.customerId=b.id\n" +
                "LEFT JOIN T_CAMPAIGN c\n" +
                "on D.campaignId = c.id WHERE 1=1");

        if(!StringUtils.isEmpty(projectVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(projectVO.getCampaignId())){
            sb.append(" AND D.campaignId = #{campaignId}");
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
        //
        if(!StringUtils.isEmpty(projectVO.getSearch())){
            sb.append(" AND D.name LIKE CONCAT('%',#{search},'%') OR D.code LIKE CONCAT('%',#{search},'%')");
        }
        sb.append(" ORDER BY D.modifyTime DESC LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countProject(final ProjectVO projectVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) from T_PROJECT  as D WHERE 1=1");

        if(!StringUtils.isEmpty(projectVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(projectVO.getCampaignId())){
            sb.append(" AND D.campaignId = #{campaignId}");
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
        return sb.toString();
    }
}
