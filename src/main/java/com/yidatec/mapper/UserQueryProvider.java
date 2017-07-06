package com.yidatec.mapper;

import com.yidatec.vo.UserVO;

/**
 * Created by Qusw on 17-3-3.
 */
public class UserQueryProvider {

    public String selectUser(final UserVO user)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT GROUP_CONCAT(DISTINCT(UR.roleId)) `roleIds`, id,`name`,mobilePhone,avatar,email,openId,password,gender,birthday," +
                "state,createTime,creatorId,modifyTime,modifierId FROM T_USER U LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId  WHERE 1=1");
        if(user.getRoleList() != null && user.getRoleList().size() > 0){
            for(int i=0;i<user.getRoleList().size();i++) {
                if(i == 0)
                    sb.append(" AND");
                else
                    sb.append(" OR");
                sb.append(" UR.roleId=#{roleList[").append(i).append("].id}");
            }
        }

        if(user.getName() != null && !user.getName().trim().isEmpty()){
            sb.append(" AND U.name LIKE CONCAT('%',#{name},'%')");
        }
        if(user.getMobilePhone() != null && !user.getMobilePhone().trim().isEmpty()){
            sb.append(" AND U.mobilePhone LIKE CONCAT('%',#{mobilePhone},'%')");
        }



        if(user.getGender() != null){
            sb.append(" AND U.gender = #{gender}");
        }
        if(user.getState() != null){
            sb.append(" AND U.state = #{state}");
        }
        sb.append(" GROUP BY U.id");
        sb.append(" ORDER BY U.modifyTime DESC");
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }

    public String countUser(final UserVO user)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(DISTINCT(U.id)) FROM T_USER U LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId LEFT JOIN WHERE 1=1");
        if(user.getRoleList() != null && user.getRoleList().size() > 0){
            for(int i=0;i<user.getRoleList().size();i++) {
                if(i == 0)
                    sb.append(" AND");
                else
                    sb.append(" OR");
                sb.append(" UR.roleId=#{roleList[").append(i).append("].id}");
            }
        }

        if(user.getName() != null && !user.getName().trim().isEmpty()){
            sb.append(" AND U.name LIKE CONCAT('%',#{name},'%')");
        }
        if(user.getMobilePhone() != null && !user.getMobilePhone().trim().isEmpty()){
            sb.append(" AND U.mobilePhone LIKE CONCAT('%',#{mobilePhone},'%')");
        }

        if(user.getGender() != null){
            sb.append(" AND U.gender = #{gender}");
        }
        if(user.getState() != null){
            sb.append(" AND U.state = #{state}");
        }

        return sb.toString();
    }

}
