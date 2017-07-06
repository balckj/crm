package com.yidatec.mapper;

import com.yidatec.model.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author QuShengWen
 */
@Mapper
public interface RoleMapper {

    @Select("SELECT * FROM T_ROLE ORDER BY modifyTime DESC;")
    List<Role> findAll();

    @Select("SELECT * FROM T_ROLE WHERE state = 1 ORDER BY modifyTime DESC;")
    List<Role> findEnable();

    @Select("SELECT * FROM T_USER_ROLE UR LEFT JOIN T_ROLE R ON UR.roleId = R.id WHERE UR.userId = #{userId};")
    List<Role> findByUserId(String userId);

    @Select("SELECT * FROM T_ROLE WHERE `name` = #{name};")
    Role findByName(String name);

    @Select("SELECT * FROM T_ROLE WHERE `id` = #{id};")
    Role findById(String id);

    @Insert("INSERT INTO T_ROLE (id,`name`,state,`desc`,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{name}," +
            "#{state},#{desc},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int create(Role role);

    @Update("UPDATE T_ROLE SET `name`=#{name},state=#{state},`desc`=#{desc},modifierId=#{modifierId}," +
            "modifyTime=#{modifyTime} WHERE id=#{id}")
    int update(Role role);
}
