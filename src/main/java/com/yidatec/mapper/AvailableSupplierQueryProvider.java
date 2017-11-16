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

    // 未选择类型
    public String selectAvailableSupplierList(final AvailableSupplierVO availableSupplierVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(" select * from ( ");
        sb.append(" select F.id,F.`name`,2 `type`,F.city,F.`platformLevel`,count(C.id) as `count` from T_FACTORY F ");
        sb.append(" LEFT JOIN T_PROJECT_FACTORY PF ON PF.factoryId = F.id");
        sb.append(" LEFT JOIN T_PROJECT P ON P.id = PF.projectId");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = P.id");
        sb.append(" where F.id not in ( ");
        sb.append(" select vendorId from T_VENDOR_APPOINTMENT ");
        sb.append(" where `type`=2 ");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start` ) between Date(#{startTime}) and  Date(#{endTime})) ");
        }

        sb.append(" )");
        sb.append(" group by F.id ");
        sb.append(" union ");
        sb.append("select ff.id,ff.`name`,ff.`type`,ff.city,ff.`platformLevel`,ff.`cidCount` from (");
        sb.append(" select  gg.id ,gg.`name`,1 `type`,gg.`city`,gg.`platformLevel`,count(C.id) `cidCount` from ");
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
        sb.append(" ) ");
        sb.append(" GROUP BY id");
        sb.append(" ) gg ");
        sb.append(" LEFT JOIN T_PROJECT_DESIGNER PD ON PD.designerId = gg.id");
        sb.append(" LEFT JOIN T_PROJECT P ON P.id = PD.projectId");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = P.id");
        sb.append(" where gg.id not in");
        sb.append(" ( ");
        sb.append(" select vendorId from T_VENDOR_APPOINTMENT ");
        sb.append(" where `type`=1 ");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");
        sb.append(" GROUP BY gg.id) ff");
        sb.append(" LEFT JOIN T_PROJECT PM ON PM.pmId = ff.id");
        sb.append(" LEFT JOIN T_CONTRACT PMC ON PMC.projectId = PM.id");

        sb.append(" LEFT JOIN T_PROJECT PDEV ON PDEV.developSaleId = ff.id");
        sb.append(" LEFT JOIN T_CONTRACT PDEVC ON PDEVC.projectId = PDEV.id");

        sb.append(" LEFT JOIN T_PROJECT PTRAC ON PTRAC.traceSaleId = ff.id");
        sb.append(" LEFT JOIN T_CONTRACT PTRACC ON PTRACC.projectId = PTRAC.id");

        sb.append(" group by ff.id");

        sb.append(" ) tmp  ");

        sb.append(" where 1=1 ");

        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and tmp.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and tmp.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and tmp.platformLevel =  #{platformLevel}");
        }
        sb.append(" LIMIT #{start},#{length}");

        return sb.toString();
    }
    public String countSelectAvailableSupplierList(final AvailableSupplierVO availableSupplierVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(" select count(*) from ( ");
        sb.append(" select * from ( ");
        sb.append(" select F.id,F.`name`,2 `type`,F.city,F.`platformLevel`,count(C.id) as `count` from T_FACTORY F ");
        sb.append(" LEFT JOIN T_PROJECT_FACTORY PF ON PF.factoryId = F.id");
        sb.append(" LEFT JOIN T_PROJECT P ON P.id = PF.projectId");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = P.id");
        sb.append(" where F.id not in ( ");
        sb.append(" select vendorId from T_VENDOR_APPOINTMENT ");
        sb.append(" where `type`=2 ");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start` ) between Date(#{startTime}) and  Date(#{endTime})) ");
        }

        sb.append(" )");
        sb.append(" group by F.id ");
        sb.append(" union ");
        sb.append("select ff.id,ff.`name`,ff.`type`,ff.city,ff.`platformLevel`,ff.`cidCount` from (");
        sb.append(" select  gg.id ,gg.`name`,1 `type`,gg.`city`,gg.`platformLevel`,count(C.id) `cidCount` from ");
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
        sb.append(" ) ");
        sb.append(" GROUP BY id");
        sb.append(" ) gg ");
        sb.append(" LEFT JOIN T_PROJECT_DESIGNER PD ON PD.designerId = gg.id");
        sb.append(" LEFT JOIN T_PROJECT P ON P.id = PD.projectId");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = P.id");
        sb.append(" where gg.id not in");
        sb.append(" ( ");
        sb.append(" select vendorId from T_VENDOR_APPOINTMENT ");
        sb.append(" where `type`=1 ");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");
        sb.append(" GROUP BY gg.id) ff");
        sb.append(" LEFT JOIN T_PROJECT PM ON PM.pmId = ff.id");
        sb.append(" LEFT JOIN T_CONTRACT PMC ON PMC.projectId = PM.id");

        sb.append(" LEFT JOIN T_PROJECT PDEV ON PDEV.developSaleId = ff.id");
        sb.append(" LEFT JOIN T_CONTRACT PDEVC ON PDEVC.projectId = PDEV.id");

        sb.append(" LEFT JOIN T_PROJECT PTRAC ON PTRAC.traceSaleId = ff.id");
        sb.append(" LEFT JOIN T_CONTRACT PTRACC ON PTRACC.projectId = PTRAC.id");

        sb.append(" group by ff.id");

        sb.append(" ) tmp  ");

        sb.append(" where 1=1 ");

        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and tmp.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and tmp.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and tmp.platformLevel =  #{platformLevel}");
        }
        sb.append(" ) a ");
        return sb.toString();
    }

    // 工厂
    public String selectAvailableSupplierListF(final AvailableSupplierVO availableSupplierVO){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT");
        sb.append(" F.id,");
        sb.append(" F.`name`,");
        sb.append(" 2 `type`,");
        sb.append(" F.city,");
        sb.append(" F.`platformLevel`,");
        sb.append(" count(C.id) AS `count`");
        sb.append(" FROM");
        sb.append(" T_FACTORY F");
        sb.append(" LEFT JOIN T_PROJECT_FACTORY PF ON PF.factoryId = F.id");
        sb.append(" LEFT JOIN T_PROJECT P ON P.id = PF.projectId");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = P.id");
        sb.append(" WHERE 1=1 ");
        sb.append(" and F.id NOT IN (");
        sb.append(" SELECT");
        sb.append(" vendorId");
        sb.append(" FROM");
        sb.append(" T_VENDOR_APPOINTMENT");
        sb.append(" WHERE");
        sb.append(" `type` = 2");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");

        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and F.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and F.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and F.platformLevel =  #{platformLevel}");
        }

        sb.append(" GROUP BY");
        sb.append(" F.id");

        sb.append(" LIMIT #{start},#{length}");

        return sb.toString();
    }
    public String countSelectAvailableSupplierListF(final AvailableSupplierVO availableSupplierVO){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT count(*) from (");
        sb.append(" SELECT");
        sb.append(" F.id,");
        sb.append(" F.`name`,");
        sb.append(" 2 `type`,");
        sb.append(" F.city,");
        sb.append(" F.`platformLevel`,");
        sb.append(" count(C.id) AS `count`");
        sb.append(" FROM");
        sb.append(" T_FACTORY F");
        sb.append(" LEFT JOIN T_PROJECT_FACTORY PF ON PF.factoryId = F.id");
        sb.append(" LEFT JOIN T_PROJECT P ON P.id = PF.projectId");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = P.id");
        sb.append(" WHERE 1=1 ");
        sb.append(" and F.id NOT IN (");
        sb.append(" SELECT");
        sb.append(" vendorId");
        sb.append(" FROM");
        sb.append(" T_VENDOR_APPOINTMENT");
        sb.append(" WHERE");
        sb.append(" `type` = 2");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");

        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and F.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and F.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and F.platformLevel =  #{platformLevel}");
        }

        sb.append(" GROUP BY");
        sb.append(" F.id");

        sb.append(" ) as A");

        return sb.toString();
    }

    // 设计师
    public String selectAvailableSupplierListD(final AvailableSupplierVO availableSupplierVO){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT");
        sb.append(" ff.id,");
        sb.append(" ff.`name`,");
        sb.append(" ff.`type`,");
        sb.append(" ff.city,");
        sb.append(" ff.`platformLevel`,");
        sb.append(" ff.`cidCount` AS `count`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append(" gg.id,");
        sb.append(" gg.`name`,");
        sb.append(" 1 `type`,");
        sb.append(" gg.`city`,");
        sb.append(" gg.`platformLevel`,");
        sb.append(" count(C.id) `cidCount`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append(" *");
        sb.append(" FROM");
        sb.append(" T_USER U");
        sb.append(" LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId");
        sb.append(" WHERE");
        sb.append(" UR.roleId IN (");
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
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" id");
        sb.append(" ) gg");
        sb.append(" LEFT JOIN T_PROJECT_DESIGNER PD ON PD.designerId = gg.id");
        sb.append(" LEFT JOIN T_PROJECT P ON P.id = PD.projectId");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = P.id");
        sb.append(" WHERE");
        sb.append(" gg.id NOT IN (");
        sb.append(" SELECT");
        sb.append(" vendorId");
        sb.append(" FROM");
        sb.append(" T_VENDOR_APPOINTMENT");
        sb.append(" WHERE `type` = 1");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" gg.id");
        sb.append(" ) ff");
        sb.append(" WHERE 1 = 1");
        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and ff.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and ff.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and ff.platformLevel =  #{platformLevel}");
        }
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countSelectAvailableSupplierListD(final AvailableSupplierVO availableSupplierVO){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT count(*) from (");
        sb.append(" SELECT");
        sb.append(" ff.id,");
        sb.append(" ff.`name`,");
        sb.append(" ff.`type`,");
        sb.append(" ff.city,");
        sb.append(" ff.`platformLevel`,");
        sb.append(" ff.`cidCount` AS `count`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append(" gg.id,");
        sb.append(" gg.`name`,");
        sb.append(" 1 `type`,");
        sb.append(" gg.`city`,");
        sb.append(" gg.`platformLevel`,");
        sb.append(" count(C.id) `cidCount`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append(" *");
        sb.append(" FROM");
        sb.append(" T_USER U");
        sb.append(" LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId");
        sb.append(" WHERE");
        sb.append(" UR.roleId IN (");
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
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" id");
        sb.append(" ) gg");
        sb.append(" LEFT JOIN T_PROJECT_DESIGNER PD ON PD.designerId = gg.id");
        sb.append(" LEFT JOIN T_PROJECT P ON P.id = PD.projectId");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = P.id");
        sb.append(" WHERE");
        sb.append(" gg.id NOT IN (");
        sb.append(" SELECT");
        sb.append(" vendorId");
        sb.append(" FROM");
        sb.append(" T_VENDOR_APPOINTMENT");
        sb.append(" WHERE `type` = 1");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" gg.id");
        sb.append(" ) ff");
        sb.append(" WHERE 1 = 1");
        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and ff.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and ff.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and ff.platformLevel =  #{platformLevel}");
        }
        sb.append(" ) as a ");
        return  sb.toString();
    }

    // 项目经理
    public String selectAvailableSupplierListP(final AvailableSupplierVO availableSupplierVO){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT");
        sb.append(" ff.id,");
        sb.append(" ff.`name`,");
        sb.append(" ff.`type`,");
        sb.append(" ff.city,");
        sb.append(" ff.`platformLevel`,");
        sb.append(" ff.`cidCount` AS `count`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append(" gg.id,");
        sb.append(" gg.`name`,");
        sb.append(" 1 `type`,");
        sb.append(" gg.`city`,");
        sb.append(" gg.`platformLevel`,");
        sb.append(" count(C.id) `cidCount`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append("  *");
        sb.append("  FROM");
        sb.append(" T_USER U");
        sb.append(" LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId");
        sb.append(" WHERE");
        sb.append(" UR.roleId IN (");
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
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" id");
        sb.append(" ) gg");
        sb.append(" LEFT JOIN T_PROJECT PM ON PM.pmId = gg.id");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = PM.id");

        sb.append(" WHERE");
        sb.append(" gg.id NOT IN (");
        sb.append(" SELECT");
        sb.append(" vendorId");
        sb.append(" FROM");
        sb.append(" T_VENDOR_APPOINTMENT");
        sb.append(" WHERE");
        sb.append(" `type` = 1");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" gg.id");
        sb.append(" ) ff");
        sb.append(" WHERE");
        sb.append(" 1 = 1");
        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and ff.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and ff.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and ff.platformLevel =  #{platformLevel}");
        }
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countSelectAvailableSupplierListP(final AvailableSupplierVO availableSupplierVO){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT count(*) from (");
        sb.append(" SELECT");
        sb.append(" ff.id,");
        sb.append(" ff.`name`,");
        sb.append(" ff.`type`,");
        sb.append(" ff.city,");
        sb.append(" ff.`platformLevel`,");
        sb.append(" ff.`cidCount` AS `count`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append(" gg.id,");
        sb.append(" gg.`name`,");
        sb.append(" 1 `type`,");
        sb.append(" gg.`city`,");
        sb.append(" gg.`platformLevel`,");
        sb.append(" count(C.id) `cidCount`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append("  *");
        sb.append("  FROM");
        sb.append(" T_USER U");
        sb.append(" LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId");
        sb.append(" WHERE");
        sb.append(" UR.roleId IN (");
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
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" id");
        sb.append(" ) gg");
        sb.append(" LEFT JOIN T_PROJECT PM ON PM.pmId = gg.id");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = PM.id");

        sb.append(" WHERE");
        sb.append(" gg.id NOT IN (");
        sb.append(" SELECT");
        sb.append(" vendorId");
        sb.append(" FROM");
        sb.append(" T_VENDOR_APPOINTMENT");
        sb.append(" WHERE");
        sb.append(" `type` = 1");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" gg.id");
        sb.append(" ) ff");
        sb.append(" WHERE");
        sb.append(" 1 = 1");
        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and ff.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and ff.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and ff.platformLevel =  #{platformLevel}");
        }
        sb.append(" ) a ");
        return sb.toString();
    }

    //  开发销售
    public String selectAvailableSupplierListDS(final AvailableSupplierVO availableSupplierVO){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT");
        sb.append(" ff.id,");
        sb.append(" ff.`name`,");
        sb.append(" ff.`type`,");
        sb.append(" ff.city,");
        sb.append(" ff.`platformLevel`,");
        sb.append(" ff.`cidCount` AS `count`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append(" gg.id,");
        sb.append(" gg.`name`,");
        sb.append(" 1 `type`,");
        sb.append(" gg.`city`,");
        sb.append(" gg.`platformLevel`,");
        sb.append(" count(C.id) `cidCount`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append("  *");
        sb.append("  FROM");
        sb.append(" T_USER U");
        sb.append(" LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId");
        sb.append(" WHERE");
        sb.append(" UR.roleId IN (");
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
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" id");
        sb.append(" ) gg");
        sb.append(" LEFT JOIN T_PROJECT PDEV ON PDEV.developSaleId = gg.id");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = PDEV.id");

        sb.append(" WHERE");
        sb.append(" gg.id NOT IN (");
        sb.append(" SELECT");
        sb.append(" vendorId");
        sb.append(" FROM");
        sb.append(" T_VENDOR_APPOINTMENT");
        sb.append(" WHERE");
        sb.append(" `type` = 1");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" gg.id");
        sb.append(" ) ff");
        sb.append(" WHERE");
        sb.append(" 1 = 1");
        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and ff.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and ff.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and ff.platformLevel =  #{platformLevel}");
        }
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countSelectAvailableSupplierListDS(final AvailableSupplierVO availableSupplierVO){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT count(*) from (");
        sb.append(" SELECT");
        sb.append(" ff.id,");
        sb.append(" ff.`name`,");
        sb.append(" ff.`type`,");
        sb.append(" ff.city,");
        sb.append(" ff.`platformLevel`,");
        sb.append(" ff.`cidCount` AS `count`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append(" gg.id,");
        sb.append(" gg.`name`,");
        sb.append(" 1 `type`,");
        sb.append(" gg.`city`,");
        sb.append(" gg.`platformLevel`,");
        sb.append(" count(C.id) `cidCount`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append("  *");
        sb.append("  FROM");
        sb.append(" T_USER U");
        sb.append(" LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId");
        sb.append(" WHERE");
        sb.append(" UR.roleId IN (");
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
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" id");
        sb.append(" ) gg");
        sb.append(" LEFT JOIN T_PROJECT PDEV ON PDEV.developSaleId = gg.id");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = PDEV.id");

        sb.append(" WHERE");
        sb.append(" gg.id NOT IN (");
        sb.append(" SELECT");
        sb.append(" vendorId");
        sb.append(" FROM");
        sb.append(" T_VENDOR_APPOINTMENT");
        sb.append(" WHERE");
        sb.append(" `type` = 1");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" gg.id");
        sb.append(" ) ff");
        sb.append(" WHERE");
        sb.append(" 1 = 1");
        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and ff.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and ff.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and ff.platformLevel =  #{platformLevel}");
        }
        sb.append(" ) a ");
        return sb.toString();
    }

    //  跟进销售
    public String selectAvailableSupplierListFS(final AvailableSupplierVO availableSupplierVO){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT");
        sb.append(" ff.id,");
        sb.append(" ff.`name`,");
        sb.append(" ff.`type`,");
        sb.append(" ff.city,");
        sb.append(" ff.`platformLevel`,");
        sb.append(" ff.`cidCount` AS `count`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append(" gg.id,");
        sb.append(" gg.`name`,");
        sb.append(" 1 `type`,");
        sb.append(" gg.`city`,");
        sb.append(" gg.`platformLevel`,");
        sb.append(" count(C.id) `cidCount`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append("  *");
        sb.append("  FROM");
        sb.append(" T_USER U");
        sb.append(" LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId");
        sb.append(" WHERE");
        sb.append(" UR.roleId IN (");
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
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" id");
        sb.append(" ) gg");
        sb.append(" LEFT JOIN T_PROJECT PTRAC ON PTRAC.traceSaleId = gg.id");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = PTRAC.id");

        sb.append(" WHERE");
        sb.append(" gg.id NOT IN (");
        sb.append(" SELECT");
        sb.append(" vendorId");
        sb.append(" FROM");
        sb.append(" T_VENDOR_APPOINTMENT");
        sb.append(" WHERE");
        sb.append(" `type` = 1");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" gg.id");
        sb.append(" ) ff");
        sb.append(" WHERE");
        sb.append(" 1 = 1");
        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and ff.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and ff.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and ff.platformLevel =  #{platformLevel}");
        }
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countSelectAvailableSupplierListFS(final AvailableSupplierVO availableSupplierVO){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT count(*) from (");
        sb.append(" SELECT");
        sb.append(" ff.id,");
        sb.append(" ff.`name`,");
        sb.append(" ff.`type`,");
        sb.append(" ff.city,");
        sb.append(" ff.`platformLevel`,");
        sb.append(" ff.`cidCount` AS `count`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append(" gg.id,");
        sb.append(" gg.`name`,");
        sb.append(" 1 `type`,");
        sb.append(" gg.`city`,");
        sb.append(" gg.`platformLevel`,");
        sb.append(" count(C.id) `cidCount`");
        sb.append(" FROM");
        sb.append(" (");
        sb.append(" SELECT");
        sb.append("  *");
        sb.append("  FROM");
        sb.append(" T_USER U");
        sb.append(" LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId");
        sb.append(" WHERE");
        sb.append(" UR.roleId IN (");
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
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" id");
        sb.append(" ) gg");
        sb.append(" LEFT JOIN T_PROJECT PTRAC ON PTRAC.traceSaleId = gg.id");
        sb.append(" LEFT JOIN T_CONTRACT C ON C.projectId = PTRAC.id");

        sb.append(" WHERE");
        sb.append(" gg.id NOT IN (");
        sb.append(" SELECT");
        sb.append(" vendorId");
        sb.append(" FROM");
        sb.append(" T_VENDOR_APPOINTMENT");
        sb.append(" WHERE");
        sb.append(" `type` = 1");
        if(!StringUtils.isEmpty(availableSupplierVO.getStartTime()) && !StringUtils.isEmpty(availableSupplierVO.getEndTime())){
            sb.append(" and (Date(`start`) between Date(#{startTime}) and  Date(#{endTime}))");
        }
        sb.append(" )");
        sb.append(" GROUP BY");
        sb.append(" gg.id");
        sb.append(" ) ff");
        sb.append(" WHERE");
        sb.append(" 1 = 1");
        if (!StringUtils.isEmpty(availableSupplierVO.getName())){
            sb.append(" and ff.`name` LIKE CONCAT('%',#{name},'%') ");
        }
        if (!StringUtils.isEmpty(availableSupplierVO.getCity())){
            sb.append(" and ff.`city` LIKE CONCAT('%',#{city},'%') ");
        }
        if(!StringUtils.isEmpty(availableSupplierVO.getPlatformLevel())){
            sb.append(" and ff.platformLevel =  #{platformLevel}");
        }
        sb.append(" ) a ");
        return sb.toString();
    }
}
