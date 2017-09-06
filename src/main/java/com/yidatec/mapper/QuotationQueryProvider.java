package com.yidatec.mapper;

import com.yidatec.vo.QuotationVO;

/**
 * Created by jrw on 17-7-18.
 */
public class QuotationQueryProvider {

    public String quotationDownLoad()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("");
        sb.append(" SELECT");
        sb.append(" a.id,");
        sb.append(" d.id dicId,");
        sb.append(" d.value AS categoryName,");
        sb.append(" b.name AS productName ,");
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
        sb.append(" ORDER BY d.value");

        return sb.toString();
    }


    public String selectQuotationList(final QuotationVO quotationVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("");
        sb.append(" SELECT");
        sb.append("  a.id,");
        sb.append(" d.id dicId,");
        sb.append(" d.value AS categoryName,");
        sb.append(" b.name AS productName ,");
        sb.append(" c.value AS unitName,");
        sb.append(" a.count ,");
        sb.append(" a.unitPrice ,");
        sb.append(" a.count *  a.unitPrice as countPrice,");
        sb.append(" a.remark ");
        sb.append(" FROM T_QUOTATION a");
        sb.append(" LEFT JOIN T_PRODUCTION AS b ON b.id = a.productionId");
        sb.append(" LEFT JOIN T_DICTIONARY AS c ON b.unit = c.id");
        sb.append(" LEFT JOIN T_DICTIONARY AS d ON b.category = d.id");
        sb.append(" ORDER BY d.value");

//        if(!StringUtils.isEmpty(quotationVO.getCategoryName())){
//            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
//        }
//
//        if(!StringUtils.isEmpty(quotationVO.getProductName())){
//            sb.append(" AND D.nature = #{nature}");
//        }
//        if(!StringUtils.isEmpty(quotationVO.getUnitName())){
//            sb.append(" AND D.address LIKE CONCAT('%',#{address},'%')");
//        }
//        sb.append(" ORDER BY D.modifyTime DESC LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countQuotationList(final QuotationVO quotationVO)
    {
        StringBuffer sb = new StringBuffer();
//        sb.append("SELECT count(*) from T_CUSTOMER  as D WHERE 1=1");

        sb.append("");
        sb.append(" SELECT count(*)");
        sb.append(" FROM T_QUOTATION a");
        sb.append(" LEFT JOIN T_PRODUCTION AS b ON b.id = a.productionId");
        sb.append(" LEFT JOIN T_DICTIONARY AS c ON b.unit = c.id");
        sb.append(" LEFT JOIN T_DICTIONARY AS d ON b.category = d.id");
        sb.append(" ORDER BY d.value");

//        if(!StringUtils.isEmpty(quotationVO.getCategoryName())){
//            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
//        }
//        if(!StringUtils.isEmpty(quotationVO.getProductName())){
//            sb.append(" AND D.nature = #{nature}");
//        }
//        if(!StringUtils.isEmpty(quotationVO.getUnitName())){
//            sb.append(" AND D.address = #{address}");
//        }
//        if(!StringUtils.isEmpty(quotationVO.getCreatorId())){
//            sb.append(" AND D.creatorId = #{creatorId}");
//        }
        return sb.toString();
    }


}
