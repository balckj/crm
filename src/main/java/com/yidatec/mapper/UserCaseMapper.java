package com.yidatec.mapper;

import com.yidatec.model.Case;
import com.yidatec.model.UserCase;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jrw on 2017/8/3.
 */
@Mapper
public interface UserCaseMapper {

    @Insert("INSERT INTO T_USER_CASE (designerId,caseId) VALUES (#{designerId},#{caseId})")
    int createUserCase(UserCase userCase);

    @Delete("DELETE FROM T_USER_CASE WHERE designerId=#{designerId}")
    int deleteUserCase(String designerId);

    @Select("SELECT * FROM T_CASE  WHERE id in (SELECT caseId FROM T_USER_CASE WHERE designerId=#{designerId})")
    List<Case> getCaseList(String designerId);
}
