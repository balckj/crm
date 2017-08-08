package com.yidatec.mapper;

import com.yidatec.model.Case;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
@Mapper
public interface CaseMapper {

    @Insert("INSERT INTO T_CASE (id,caseName,casePhoto,type) VALUES (#{id},#{caseName},#{casePhoto},#{type})")
    int createCase(Case cases);

    @Delete("DELETE FROM T_CASE  WHERE id in( SELECT caseId FROM T_FACTORY_CONTACT WHERE factoryId=#{factoryId})")
    int deleteCase(String factoryId);

    @Select("SELECT * FROM T_CASE WHERE id in( SELECT caseId FROM T_FACTORY_CONTACT WHERE factoryId=#{factoryId})")
    List<Case> getCase(String factoryId);
}