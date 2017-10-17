package com.yidatec.mapper;

import com.yidatec.model.User;
import com.yidatec.vo.SaleVO;
import com.yidatec.vo.UserVO;
import org.springframework.util.StringUtils;

/**
 * Created by jrw on 17-7-18.
 */
public class SaleQueryProvider {

    public String selectUserByRoleCommon(final UserVO userVO) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM T_USER U LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId WHERE 1=1");

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
            sb.append(" AND U.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(userVO.getMobilePhone())){
            sb.append(" AND U.mobilePhone = #{mobilePhone}");
        }
        if(!StringUtils.isEmpty(userVO.getState())){
            sb.append(" AND U.state = #{state}");
        }
        sb.append(" GROUP BY U.id ORDER BY modifyTime DESC");
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }

    public String countUserByRoleCommon(final UserVO userVO) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) FROM ( ");
        sb.append("SELECT *  FROM T_USER U LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId WHERE 1=1");
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
            sb.append(" AND U.name LIKE CONCAT('%',#{name},'%')");
        }
        if(!StringUtils.isEmpty(userVO.getMobilePhone())){
            sb.append(" AND U.mobilePhone = #{mobilePhone}");
        }
        if(!StringUtils.isEmpty(userVO.getState())){
            sb.append(" AND U.state = #{state}");
        }
        sb.append(" GROUP BY U.id ORDER BY modifyTime DESC");
        sb.append(" LIMIT #{start},#{length}");
        sb.append(" ) as A");
        return sb.toString();
    }

    // 推荐人
    public String selectSaleListALL(final UserVO userVO) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM T_USER U LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId WHERE 1=1");
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

        sb.append(" and U.state = 1 GROUP BY U.id ORDER BY modifyTime DESC");
        return sb.toString();
    }
}
