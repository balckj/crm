package com.yidatec.mapper;


import com.yidatec.model.Param;
import com.yidatec.vo.AvailableSupplierVO;
import com.yidatec.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * 可用供应商列表Mapper
 */
public class AvailableSupplierQueryProvider {


    public String selectAvailableSupplierList(final AvailableSupplierVO availableSupplierVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(" select * from ( ");
        sb.append(" select id,`name`,2 `type`,city,`platformLevel` from T_FACTORY F ");
        sb.append(" where F.id not in ( ");
        sb.append(" select vendorId from T_VENDOR_APPOINTMENT ");
        sb.append(" where `type`=2 ");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start` ) between Date(#{startTime}) and  Date(#{endTime})) ");
        }
        sb.append(" )");
        sb.append(" union ");
        sb.append(" select id,`name`,1 `type`,city,`platformLevel` from ");
        sb.append(" ( ");
        sb.append(" select * from T_USER U left join T_USER_ROLE UR on U.id = UR.userId ");
        sb.append(" where UR.roleId in ( ");
        if (availableSupplierVO.getParamList() != null){
            List<Param> paramList = availableSupplierVO.getParamList();
            String temp = "";
            for (int i = 0 ; i < paramList.size() ; i ++){
                Param s = paramList.get(i);
                String[] valus = s.getValue().split(",");
                for (int i1 = 0; i1 < valus.length; i1++) {
                    String roleId = valus[i1];
                    temp +="'" + roleId + "'" + ",";
                }
            }
            if (!StringUtils.isEmpty(temp)){
                temp = temp.substring(0,temp.length()-1);
                sb.append(temp);
            }
        }
        sb.append(" )) gg where gg.id not in");
        sb.append(" ( ");
        sb.append(" select vendorId from T_VENDOR_APPOINTMENT ");
        sb.append(" where `type`=1 ");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )) tmp  ");
        sb.append(" where 1=1 ");
        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and tmp.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        sb.append(" LIMIT #{start},#{length}");

        return sb.toString();
    }


    public String countSelectAvailableSupplierList(final AvailableSupplierVO availableSupplierVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(" select count(*) from ( ");
        sb.append(" select * from ( ");
        sb.append(" select id,`name`,2 `type`,city,`platformLevel` from T_FACTORY F ");
        sb.append(" where F.id not in ( ");
        sb.append(" select vendorId from T_VENDOR_APPOINTMENT ");
        sb.append(" where `type`=2 ");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start` ) between Date(#{startTime}) and  Date(#{endTime})) ");
        }
        sb.append(" )");
        sb.append(" union ");
        sb.append(" select id,`name`,1 `type`,city,`platformLevel` from ");
        sb.append(" ( ");
        sb.append(" select * from T_USER U left join T_USER_ROLE UR on U.id = UR.userId ");
        sb.append(" where UR.roleId in ( ");
        if (availableSupplierVO.getParamList() != null){
            List<Param> paramList = availableSupplierVO.getParamList();
            String temp = "";
            for (int i = 0 ; i < paramList.size() ; i ++){
                Param s = paramList.get(i);
                String[] valus = s.getValue().split(",");
                for (int i1 = 0; i1 < valus.length; i1++) {
                    String roleId = valus[i1];
                    temp +="'" + roleId + "'" + ",";
                }
            }
            if (!StringUtils.isEmpty(temp)){
                temp = temp.substring(0,temp.length()-1);
                sb.append(temp);
            }
        }
        sb.append(" )) gg where gg.id not in");
        sb.append(" ( ");
        sb.append(" select vendorId from T_VENDOR_APPOINTMENT ");
        sb.append(" where `type`=1 ");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )) tmp  ");
        sb.append(" where 1=1 ");
        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and tmp.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        sb.append(" ) a ");
        return sb.toString();
    }
}
