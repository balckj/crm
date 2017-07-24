package com.yidatec.mapper;

import com.yidatec.model.Param;
import com.yidatec.model.Role;
import com.yidatec.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jrw on 2017/7/21.
 */
@Mapper
public interface ParamMapper {


//    @Select("SELECT * FROM T_ROLE AS R LEFT JOIN T_PARAMS   WHERE id=#{id}")
//    public List<Role> findRoles(String fixedId);

    public List<User> findUsers(String fixedId);

    @Select("SELECT * FROM T_PARAMS WHERE id=#{id}")
    Param findParamById(String id);

    @Select("SELECT * FROM T_PARAMS")
    List<Param> findAllParam();

    @Insert("INSERT INTO T_PARAMS (id,`title`,value" +
            ") VALUES (#{id},#{title}," +
            "#{value})")
    int create(Param param);

    @Delete("DELETE FROM T_PARAMS WHERE id=#{id}")
    int delete(String id);
}
