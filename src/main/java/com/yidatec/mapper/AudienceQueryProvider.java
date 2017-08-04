package com.yidatec.mapper;

import com.yidatec.vo.AudienceVO;
import com.yidatec.vo.UserVO;
import org.springframework.util.StringUtils;

/**
 * Created by jrw on 17-7-18.
 */
public class AudienceQueryProvider {

    public String selectAudienceList(final AudienceVO audienceVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM T_AUDIENCE U " +
                " WHERE 1=1 ");

        if (!StringUtils.isEmpty(audienceVO.getName())){
            sb.append(" AND U.name LIKE CONCAT('%',#{name},'%')");
        }

        sb.append(" ORDER BY U.modifyTime DESC");
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countSelectAudienceList(final AudienceVO audienceVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) FROM T_AUDIENCE U " +
                " WHERE 1=1 ");

        if (!StringUtils.isEmpty(audienceVO.getName())){
            sb.append(" AND U.name LIKE CONCAT('%',#{name},'%')");
        }

        sb.append(" ORDER BY U.modifyTime DESC");
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }

}
