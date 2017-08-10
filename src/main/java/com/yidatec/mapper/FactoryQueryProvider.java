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
        sb.append("SELECT B.name as referrer,A.* FROM T_FACTORY A LEFT JOIN T_USER B ON A.referrer = B.id");

        if(!StringUtils.isEmpty(factory.getFactoryName())){
            sb.append(" AND D.factoryName LIKE CONCAT('%',#{factoryName},'%')");
        }
//        if (!StringUtils.isEmpty(factory.getUserList())){
//            if(!StringUtils.isEmpty(factory.getUserList().get(0).getName())){
//                sb.append(" AND D.companyName LIKE CONCAT('%',#{companyName},'%')");
//            }
//        }

//        if(!StringUtils.isEmpty(factory.getUserList().get(0).getName())){
//            sb.append(" AND D.companyId = #{companyId}");
//        }
        if(!StringUtils.isEmpty(factory.getAddress())){
            sb.append(" AND D.address LIKE CONCAT('%',#{address},'%')");
        }
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countFactory(final FactoryVO factory)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) from T_FACTORY  as D WHERE 1=1");

        if(!StringUtils.isEmpty(factory.getFactoryName())){
            sb.append(" AND D.factoryName LIKE CONCAT('%',#{factoryName},'%')");
        }
//        if(!StringUtils.isEmpty(factory.getUserList().get(0).getName())){
//            sb.append(" AND D.companyId = #{companyId}");
//        }
        if(!StringUtils.isEmpty(factory.getAddress())){
            sb.append(" AND D.address LIKE CONCAT('%',#{address},'%')");
        }
        return sb.toString();
    }
}
