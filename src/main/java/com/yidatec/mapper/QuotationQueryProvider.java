package com.yidatec.mapper;

/**
 * Created by jrw on 17-7-18.
 */
public class QuotationQueryProvider {

    public String quotationDownLoad()
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

        return sb.toString();
    }
}
