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
        if(!StringUtils.isEmpty(customerVO.getCreatorId())){
            sb.append(" AND D.creatorId = #{creatorId}");
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
        if(!StringUtils.isEmpty(customerVO.getCreatorId())){
            sb.append(" AND D.creatorId = #{creatorId}");
        }
        return sb.toString();
    }

    public String customerDownLoad(String startTime,String endTime)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("");
        sb.append(" SELECT a.id,");
        sb.append(" c.`name` as saleName,");
        sb.append(" b.`name` as customerName,");
        sb.append(" d.`value` as industry,");//行业
        sb.append(" h.`name` as campaignName,");
        sb.append(" b.country,");
        sb.append(" b.province,");
        sb.append(" b.city,");
        sb.append(" a.exhibitionNumber,");
        sb.append(" f.`name` as userName,");
        sb.append(" f.mobilePhone,");
        sb.append(" g.value as position,");
        sb.append(" f.email,");
        sb.append(" a.remark,");
        sb.append(" a.createTime");
        sb.append(" FROM `T_PROJECT` a");
        sb.append(" LEFT JOIN T_CUSTOMER b ON a.customerId = b.id");
        sb.append(" LEFT JOIN T_CAMPAIGN h ON a.campaignId = h.id");
        sb.append(" LEFT JOIN T_CUSTOMER_CONTACT e ON e.customerId = b.id");
        sb.append(" LEFT JOIN T_CONTACT f ON e.contactId = f.id");
        sb.append(" LEFT JOIN T_DICTIONARY g ON f.position = g.id");
        sb.append(" LEFT JOIN T_USER c ON a.developSaleId = c.id");
        sb.append(" LEFT JOIN T_DICTIONARY d ON b.industry = d.id");
        sb.append(" where a.createTime BETWEEN DATE(#{startTime}) AND DATE(#{endTime})");
        sb.append(" ORDER BY a.createTime ");
        return sb.toString();
    }
}
