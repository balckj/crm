package com.yidatec.mapper;

import com.yidatec.model.User;
import com.yidatec.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Qusw on 17-2-16.
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM T_USER;")
    List<User> findAll();

    @SelectProvider(type=com.yidatec.mapper.UserQueryProvider.class,method = "selectUser")
    List<UserVO> findUsers(UserVO user);

    @SelectProvider(type=com.yidatec.mapper.UserQueryProvider.class,method = "countUser")
    int countUsers(UserVO user);

    @Select("SELECT * FROM T_USER WHERE id=#{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(property = "roleList", column = "id",
            many = @Many(select = "com.yidatec.mapper.RoleMapper.findByUserId"))})
    User findById(String id);

    @Select("SELECT * FROM T_USER WHERE id=#{id}")
    User findByIdIngnoreRole(String id);


    @Select("SELECT * FROM T_USER WHERE mobilePhone=#{mobilePhone}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(property = "roleList", column = "id",
            many = @Many(select = "com.yidatec.mapper.RoleMapper.findByUserId"))})
    User findByMobilePhone(String mobilePhone);

    @Select("SELECT * FROM T_USER WHERE openId=#{openId}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(property = "roleList", column = "id",
            many = @Many(select = "com.yidatec.mapper.RoleMapper.findByUserId"))})
    User findByOpenId(String openId);

    @Insert("INSERT INTO T_USER (id,code,`name`,mobilePhone,openId,avatar,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{code},#{name}," +
            "#{mobilePhone},#{openId},#{avatar},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int registry(User user);

    @Insert("INSERT INTO T_USER (id,`name`,mobilePhone,password,state,gender,email," +
            "birthday,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{name}," +
            "#{mobilePhone},#{password},#{state},#{gender},#{email},#{birthday}," +
            "#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int create(User user);



    @Update("UPDATE T_USER SET `name`=#{name},mobilePhone=#{mobilePhone},password=#{password}," +
            "state=#{state},gender=#{gender},email=#{email}," +
            "birthday=#{birthday},modifierId=#{modifierId}," +
            "modifyTime=#{modifyTime} WHERE id=#{id}")
    int update(User user);

    @Select("SELECT * FROM T_USER U LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId  WHERE UR.roleId=#{roleId} " +
            "and U.state = 1 ORDER BY modifyTime DESC")
    List<UserVO> findUsersByRole(String roleId);

    @Select("SELECT * FROM T_USER U LEFT JOIN T_USER_ROLE UR ON U.id = UR.userId  WHERE UR.roleId=#{roleId}")
    List<UserVO> findUsersByRoleIngoreState(String roleId);

    @Update("UPDATE T_USER SET `openId`=#{openId} WHERE mobilePhone=#{mobilePhone} and password=#{password}")
    int bind(User user);

    @Select("select *,GROUP_CONCAT(r.`name` ) `roleNames` from T_USER u left join T_USER_ROLE ur on u.id=ur.userId left join T_ROLE r on ur.roleId = r.id group by u.id")
    List<UserVO> findAllUser();

}
