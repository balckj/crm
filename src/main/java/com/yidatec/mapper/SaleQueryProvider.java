package com.yidatec.mapper;

import com.yidatec.model.User;
import com.yidatec.vo.SaleVO;
import com.yidatec.vo.UserVO;
import org.springframework.util.StringUtils;

/**
 * Created by jrw on 17-7-18.
 */
public class SaleQueryProvider {

    public String selectSale(final UserVO userVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM T_USER  as D WHERE 1=1");

        if(!StringUtils.isEmpty(userVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }

        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countSale(final UserVO userVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) from T_USER  as D WHERE 1=1");

        if(!StringUtils.isEmpty(userVO.getName())){
            sb.append(" AND D.name LIKE CONCAT('%',#{name},'%')");
        }
        return sb.toString();
    }
}
