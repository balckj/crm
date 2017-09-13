package com.yidatec.mapper;

import com.yidatec.vo.QuotationVO;
import org.springframework.util.StringUtils;

/**
 * Created by jrw on 17-7-18.
 */
public class QuotationQueryProvider {

    public String quotationDownLoad(String id)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("");
        sb.append(" SELECT");
        sb.append(" a.id,");
        sb.append(" d.id dicId,");
        sb.append(" d.value AS categoryName,");
        sb.append(" p.name AS projectName  ,");
        sb.append(" c.value AS unitName,");
        sb.append(" pr.count ,");
        sb.append(" pr.unitPrice ,");
        sb.append(" pr.count *  pr.unitPrice as countPrice,");
        sb.append(" pr.workContent ");
        sb.append(" FROM T_QUOTATION a");
        sb.append(" LEFT JOIN T_QUOTATION_PRODUCTION AS pr ON pr.qoutationId = a.id");
        sb.append(" LEFT JOIN T_PRODUCTION AS b ON b.id = pr.productionId");
        sb.append(" LEFT JOIN T_DICTIONARY AS c ON b.unit = c.id");
        sb.append(" LEFT JOIN T_DICTIONARY AS d ON b.category = d.id");
        sb.append(" LEFT JOIN T_PROJECT as p ON  p.id = a.projectId");
        sb.append(" where 1=1 and a.id = #{id}");
        sb.append(" ORDER BY d.value");
        return sb.toString();
    }


    public String selectQuotationList(final QuotationVO quotationVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT B.name as projectName,D.* FROM T_QUOTATION  as D LEFT JOIN T_PROJECT B ON D.projectId = B.id WHERE 1=1");
        if(!StringUtils.isEmpty(quotationVO.getProjectName())){
            sb.append(" AND B.name LIKE CONCAT('%',#{projectName},'%')");
        }
        if(!StringUtils.isEmpty(quotationVO.getPriceLevel())){
            sb.append(" AND D.priceLevel=#{priceLevel}");
        }
        return sb.toString();
    }
    public String countQuotationList(final QuotationVO quotationVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) FROM T_QUOTATION  as D  LEFT JOIN T_PROJECT B ON D.projectId = B.id WHERE 1=1" );
        if(!StringUtils.isEmpty(quotationVO.getProjectName())){
            sb.append(" AND B.name LIKE CONCAT('%',#{projectName},'%')");
        }
        if(!StringUtils.isEmpty(quotationVO.getPriceLevel())){
            sb.append(" AND D.priceLevel=#{priceLevel}");
        }
        return sb.toString();
    }


}
