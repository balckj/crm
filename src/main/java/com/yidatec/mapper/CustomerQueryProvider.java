package com.yidatec.mapper;

import com.yidatec.vo.CustomerVO;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/7/19.
 */
public class CustomerQueryProvider {
    public String selectCustomer(final CustomerVO customerVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM T_CUSTOMER  as D WHERE 1=1");

        if(!StringUtils.isEmpty(customerVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(customerVO.getNature())){
            sb.append(" AND D.nature = #{nature}");
        }
        if(!StringUtils.isEmpty(customerVO.getAddress())){
            sb.append(" AND D.address LIKE CONCAT('%',#{address},'%')");
        }
        if(!StringUtils.isEmpty(customerVO.getProvince())){
            sb.append(" AND D.province LIKE CONCAT('%',#{province},'%')");
        }
        if(!StringUtils.isEmpty(customerVO.getCity())){
            sb.append(" AND D.city LIKE CONCAT('%',#{city},'%')");
        }
        if(!StringUtils.isEmpty(customerVO.getIndustry())){
            sb.append(" AND D.industry = #{industry}");
        }
        if(!StringUtils.isEmpty(customerVO.getCreatorId())){
            sb.append(" AND D.creatorId = #{creatorId}");
        }
        if(!StringUtils.isEmpty(customerVO.getState())){
            sb.append(" AND D.state = #{state}");
        }
        sb.append(" ORDER BY D.modifyTime DESC LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countCustomer(final CustomerVO customerVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) from T_CUSTOMER  as D WHERE 1=1");

        if(!StringUtils.isEmpty(customerVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(customerVO.getNature())){
            sb.append(" AND D.nature = #{nature}");
        }
        if(!StringUtils.isEmpty(customerVO.getAddress())){
            sb.append(" AND D.address LIKE CONCAT('%',#{address},'%')");
        }
        if(!StringUtils.isEmpty(customerVO.getProvince())){
            sb.append(" AND D.province LIKE CONCAT('%',#{province},'%')");
        }
        if(!StringUtils.isEmpty(customerVO.getCity())){
            sb.append(" AND D.city LIKE CONCAT('%',#{city},'%')");
        }
        if(!StringUtils.isEmpty(customerVO.getIndustry())){
            sb.append(" AND D.industry = #{industry}");
        }
        if(!StringUtils.isEmpty(customerVO.getCreatorId())){
            sb.append(" AND D.creatorId = #{creatorId}");
        }
        if(!StringUtils.isEmpty(customerVO.getState())){
            sb.append(" AND D.state = #{state}");
        }
        return sb.toString();
    }

}
