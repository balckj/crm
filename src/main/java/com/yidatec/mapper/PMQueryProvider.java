package com.yidatec.mapper;

import com.yidatec.vo.UserVO;
import org.springframework.util.StringUtils;

/**
 * Created by jrw on 17-7-18.
 */
public class PMQueryProvider {

    public String selectUserByRolePM(final UserVO userVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT A.*,count(P.Id) caseNumb FROM (");
        sb.append(" SELECT U.id,U.name,DD.referrer,U.mobilePhone ,U.country ,U.city,U.goodAtIndustry,U.englishAbility,U.goodAtArea,U.state,U.createTime,U.modifyTime  FROM T_USER U LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId " +
                " LEFT JOIN (SELECT A.id,B.name as referrer FROM T_USER AS  A INNER JOIN T_USER as B ON A.referrer = B.id) as DD ON DD.id = U.id WHERE 1=1 ");

        sb.append(" AND UR.roleId in (");

        String[] roleids = userVO.getParaRoleIDS().split(",");

        if (roleids != null){
            for (int i = 0 ; i< roleids.length ; i ++){
                String s = roleids[i];
                if(i != roleids.length -1){
                    sb.append("'" + s + "'" + ",");
                }else{
                    sb.append("'" + s + "'");
                }
            }
        }
        sb.append(")");

        if(!StringUtils.isEmpty(userVO.getName())){
            sb.append(" AND DD.referrer LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(userVO.getMobilePhone())){
            sb.append(" AND U.mobilePhone = #{mobilePhone}");
        }

        sb.append(" and U.state = 1 GROUP BY U.id");
        sb.append(" ) A");
        sb.append(" LEFT JOIN T_PROJECT P ON P.pmId = A.id");
        sb.append(" GROUP BY A.id");
        sb.append(" ORDER BY A.modifyTime DESC");

        sb.append(" LIMIT #{start},#{length}");

        return sb.toString();
    }
    public String countUserByRolePM(final UserVO userVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) FROM ( ");
        sb.append(" SELECT A.*,count(P.Id) FROM (");
        sb.append(" SELECT U.id,U.name,DD.referrer,U.mobilePhone ,U.country ,U.city,U.goodAtIndustry,U.englishAbility,U.goodAtArea,U.state,U.createTime,U.modifyTime  FROM T_USER U LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId " +
                " LEFT JOIN (SELECT A.id,B.name as referrer FROM T_USER AS  A INNER JOIN T_USER as B ON A.referrer = B.id) as DD ON DD.id = U.id WHERE 1=1 ");

        sb.append(" AND UR.roleId in (");

        String[] roleids = userVO.getParaRoleIDS().split(",");

        if (roleids != null){
            for (int i = 0 ; i< roleids.length ; i ++){
                String s = roleids[i];
                if(i != roleids.length -1){
                    sb.append("'" + s + "'" + ",");
                }else{
                    sb.append("'" + s + "'");
                }
            }
        }
        sb.append(")");

        if(!StringUtils.isEmpty(userVO.getName())){
            sb.append(" AND DD.referrer LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(userVO.getMobilePhone())){
            sb.append(" AND U.mobilePhone = #{mobilePhone}");
        }

        sb.append(" and U.state = 1 GROUP BY U.id");
        sb.append(" ) A");
        sb.append(" LEFT JOIN T_PROJECT P ON P.pmId = A.id");
        sb.append(" GROUP BY A.id");
        sb.append(" ORDER BY A.modifyTime DESC");
        sb.append(") as A");
        return sb.toString();
    }


    public String selectPMforProject(final UserVO userVO) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT U.id,U.name,DD.referrer,U.mobilePhone ,U.country ,U.city,U.goodAtIndustry,U.englishAbility,U.goodAtArea,U.state,U.createTime,U.modifyTime  FROM T_USER U LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId " +
                "LEFT JOIN (SELECT A.id,B.name as referrer FROM T_USER AS  A INNER JOIN T_USER as B ON A.referrer = B.id) as DD ON DD.id = U.id WHERE 1=1 ");

        sb.append(" AND UR.roleId in (");

        String[] roleids = userVO.getParaRoleIDS().split(",");

        if (roleids != null){
            for (int i = 0 ; i< roleids.length ; i ++){
                String s = roleids[i];
                if(i != roleids.length -1){
                    sb.append("'" + s + "'" + ",");
                }else{
                    sb.append("'" + s + "'");
                }
            }
        }
        sb.append(")");

        sb.append(" and U.state = 1 GROUP BY U.id ORDER BY U.modifyTime DESC");
        return sb.toString();
    }

}
