package com.yidatec.mapper;

import com.yidatec.vo.ExhibitionVO;
import com.yidatec.vo.ProductVO;
import org.springframework.util.StringUtils;

/**
 * Created by jrw on 17-7-18.
 */
public class ExhibitionQueryProvider {

    public String selectExhibitionList(final ExhibitionVO exhibitionVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id,name,start as startTime,address,country,province,city,area,region,createTime,modifyTime FROM T_EXHIBITION_HALL C");
        sb.append(" WHERE 1=1 ");
        if(!StringUtils.isEmpty(exhibitionVO.getName())){
            sb.append(" AND C.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(exhibitionVO.getCity())){
            sb.append(" AND C.city LIKE CONCAT('%',#{city},'%')");
        }
        if(!StringUtils.isEmpty(exhibitionVO.getRegion())){
            sb.append(" AND C.region LIKE CONCAT('%',#{region},'%')");
        }
        sb.append(" ORDER BY C.modifyTime DESC");
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countSelectExhibitionList(final ExhibitionVO exhibitionVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) FROM T_EXHIBITION_HALL C");
        sb.append(" WHERE 1=1 ");
        if(!StringUtils.isEmpty(exhibitionVO.getName())){
            sb.append(" AND C.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(exhibitionVO.getCity())){
            sb.append(" AND C.city LIKE CONCAT('%',#{city},'%')");
        }
        if(!StringUtils.isEmpty(exhibitionVO.getRegion())){
            sb.append(" AND C.region LIKE CONCAT('%',#{region},'%')");
        }
//        sb.append(" ORDER BY C.modifyTime DESC");
//        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }

}
