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

        if(!StringUtils.isEmpty(customerVO.getCompanyName())){
            sb.append(" AND D.companyName LIKE CONCAT('%',#{companyName},'%')");
        }
        if(!StringUtils.isEmpty(customerVO.getCompanyId())){
            sb.append(" AND D.companyId = #{companyId}");
        }
        if(!StringUtils.isEmpty(customerVO.getNature())){
            sb.append(" AND D.nature = #{nature}");
        }
        if(!StringUtils.isEmpty(customerVO.getAddress())){
            sb.append(" AND D.address = #{address}");
        }
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countCustomer(final CustomerVO customerVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) from T_CUSTOMER  as D WHERE 1=1");

        if(!StringUtils.isEmpty(customerVO.getCompanyName())){
            sb.append(" AND D.companyName LIKE CONCAT('%',#{companyName},'%')");
        }
        if(!StringUtils.isEmpty(customerVO.getCompanyId())){
            sb.append(" AND D.companyId = #{companyId}");
        }
        if(!StringUtils.isEmpty(customerVO.getNature())){
            sb.append(" AND D.nature = #{nature}");
        }
        if(!StringUtils.isEmpty(customerVO.getAddress())){
            sb.append(" AND D.address = #{address}");
        }
        return sb.toString();
    }
}