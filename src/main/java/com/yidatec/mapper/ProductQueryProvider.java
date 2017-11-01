package com.yidatec.mapper;

import com.yidatec.vo.ProductVO;
import org.springframework.util.StringUtils;

/**
 * Created by jrw on 17-7-18.
 */
public class ProductQueryProvider {

    public String selectProductList(final ProductVO product)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT C.id,C.name,D.category,D1.unit,D2.way,C.desc,C.unit,C.way,C.low,C.middle,C.high,C.createTime,C.modifyTime FROM T_PRODUCTION C ");
        sb.append("LEFT JOIN (SELECT a.id,b.value as category FROM T_PRODUCTION a INNER JOIN T_DICTIONARY b ON  a.category = b.id) as D ");
        sb.append("ON C.id = D.id ");
        sb.append("LEFT JOIN (SELECT a.id,b.value as unit FROM T_PRODUCTION a INNER JOIN T_DICTIONARY b ON  a.unit = b.id) as D1 ");
        sb.append("ON C.id = D1.id ");
        sb.append("LEFT JOIN (SELECT a.id,b.value as way FROM T_PRODUCTION a INNER JOIN T_DICTIONARY b ON  a.way = b.id) as D2 ");
        sb.append("ON C.id = D2.id ");
        sb.append("WHERE 1=1 ");

        if(!StringUtils.isEmpty(product.getCategory())){
            sb.append(" AND D.category LIKE CONCAT('%',#{category},'%')");
        }
        if(!StringUtils.isEmpty(product.getName())){
            sb.append(" AND C.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(product.getUnit())){
            sb.append(" AND D1.unit = #{unit}");
        }
        if(!StringUtils.isEmpty(product.getSearch())){
            sb.append(" AND D.category LIKE CONCAT('%',#{search},'%') OR C.name LIKE CONCAT('%',#{search},'%') OR D1.unit LIKE CONCAT('%',#{search},'%')");
        }

        sb.append(" ORDER BY C.modifyTime DESC");
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countSelectProductList(final ProductVO product)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) FROM T_PRODUCTION C ");
        sb.append("LEFT JOIN (SELECT a.id,b.value as category FROM T_PRODUCTION a INNER JOIN T_DICTIONARY b ON  a.category = b.id) as D ");
        sb.append("ON C.id = D.id ");
        sb.append("LEFT JOIN (SELECT a.id,b.value as unit FROM T_PRODUCTION a INNER JOIN T_DICTIONARY b ON  a.unit = b.id) as D1 ");
        sb.append("ON C.id = D1.id ");
        sb.append("LEFT JOIN (SELECT a.id,b.value as way FROM T_PRODUCTION a INNER JOIN T_DICTIONARY b ON  a.way = b.id) as D2 ");
        sb.append("ON C.id = D2.id ");
        sb.append("WHERE 1=1 ");

        if(!StringUtils.isEmpty(product.getCategory())){
            sb.append(" AND D.category LIKE CONCAT('%',#{category},'%')");
        }
        if(!StringUtils.isEmpty(product.getName())){
            sb.append(" AND C.name LIKE CONCAT('%',#{name},'%')");
        }

        if(!StringUtils.isEmpty(product.getUnit())){
            sb.append(" AND D1.unit = #{unit}");
        }
        if(!StringUtils.isEmpty(product.getSearch())){
            sb.append(" AND D.category LIKE CONCAT('%',#{search},'%') OR C.name LIKE CONCAT('%',#{search},'%') OR D1.unit LIKE CONCAT('%',#{search},'%')");
        }
        return sb.toString();
    }

}
