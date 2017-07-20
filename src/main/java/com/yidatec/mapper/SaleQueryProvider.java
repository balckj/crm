package com.yidatec.mapper;

import com.yidatec.vo.SaleVO;
import org.springframework.util.StringUtils;

/**
 * Created by jrw on 17-7-18.
 */
public class SaleQueryProvider {

    public String selectSale(final SaleVO SaleVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM T_SALE  as D WHERE 1=1");

        if(!StringUtils.isEmpty(SaleVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }

        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countSale(final SaleVO SaleVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) from T_SALE  as D WHERE 1=1");

        if(!StringUtils.isEmpty(SaleVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }
        return sb.toString();
    }
}
