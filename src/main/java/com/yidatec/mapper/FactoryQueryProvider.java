package com.yidatec.mapper;

import com.yidatec.vo.FactoryVO;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/8/1.
 */
public class FactoryQueryProvider {

    public String selectFactory(final FactoryVO factory)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT B.name as referrer,A.* FROM T_FACTORY A LEFT JOIN T_USER B ON A.referrer = B.id  where 1=1");

        if(!StringUtils.isEmpty(factory.getName())){
            sb.append(" AND A.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(factory.getProvince())){
            sb.append(" AND A.province LIKE CONCAT('%',#{province},'%')");
        }
        if(!StringUtils.isEmpty(factory.getCity())){
            sb.append(" AND A.city LIKE CONCAT('%',#{city},'%')");
        }
        if(!StringUtils.isEmpty(factory.getPlatformLevel())){
            sb.append(" AND A.platformLevel LIKE CONCAT('%',#{platformLevel},'%')");
        }
        if(!StringUtils.isEmpty(factory.getDirector())){
            sb.append(" AND A.director LIKE CONCAT('%',#{director},'%')");
        }
        if(!StringUtils.isEmpty(factory.getState())){
            sb.append(" AND A.state = #{state}");
        }
        sb.append(" ORDER BY A.modifyTime DESC");
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countFactory(final FactoryVO factory)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) FROM T_FACTORY A LEFT JOIN T_USER B ON A.referrer = B.id  where 1=1");

        if(!StringUtils.isEmpty(factory.getName())){
            sb.append(" AND A.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(factory.getProvince())){
            sb.append(" AND A.province LIKE CONCAT('%',#{province},'%')");
        }
        if(!StringUtils.isEmpty(factory.getCity())){
            sb.append(" AND A.city LIKE CONCAT('%',#{city},'%')");
        }
        if(!StringUtils.isEmpty(factory.getPlatformLevel())){
            sb.append(" AND A.platformLevel LIKE CONCAT('%',#{platformLevel},'%')");
        }
       if(!StringUtils.isEmpty(factory.getDirector())){
        sb.append(" AND A.director LIKE CONCAT('%',#{director},'%')");
        }
        if(!StringUtils.isEmpty(factory.getAddress())){
            sb.append(" AND A.address LIKE CONCAT('%',#{address},'%')");
        }
        if(!StringUtils.isEmpty(factory.getState())){
            sb.append(" AND A.state = #{state}");
        }
        return sb.toString();
    }

    public String selectFactoryListForProject(final FactoryVO factory)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM T_FACTORY A ");
        return sb.toString();
    }
}
