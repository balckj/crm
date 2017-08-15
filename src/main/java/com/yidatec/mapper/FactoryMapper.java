package com.yidatec.mapper;

import com.yidatec.model.Contact;
import com.yidatec.model.FactoryEntity;
import com.yidatec.vo.FactoryVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Mapper
public interface FactoryMapper {

    @Insert("INSERT INTO T_FACTORY (id,referrer,name,director,firstOrderTime,country,province,city,region,address,factoryArea,photo,registeredCapital,taxpayerType,fixedEmployeeCount,goodAtIndustry,goodAtMaterial,goodAtArea,platformLevel,valueAddedTaxAccount,taxNumber,state,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{referrer},#{name}," +
            "#{director},#{firstOrderTime},#{country},#{province},#{city},#{region},#{address},#{factoryArea},#{photo},#{registeredCapital},#{taxpayerType},#{fixedEmployeeCount},#{goodAtIndustry},#{goodAtMaterial},#{goodAtArea},#{platformLevel},#{valueAddedTaxAccount},#{taxNumber},#{state},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int create(FactoryEntity factory);

    @Update("UPDATE T_FACTORY SET `referrer`=#{referrer},name=#{name},director=#{director},firstOrderTime=#{firstOrderTime}," +
            "country=#{country},province=#{province},city=#{city},region=#{region},address=#{address},factoryArea=#{factoryArea},photo=#{photo},registeredCapital=#{registeredCapital}," +
            "taxpayerType=#{taxpayerType},fixedEmployeeCount=#{fixedEmployeeCount},goodAtIndustry=#{goodAtIndustry},goodAtMaterial=#{goodAtMaterial},goodAtArea=#{goodAtArea},platformLevel=#{platformLevel},valueAddedTaxAccount=#{valueAddedTaxAccount},taxNumber=#{taxNumber},state=#{state},modifierId=#{modifierId}," +
            "modifyTime=#{modifyTime} WHERE id=#{id}")
    int update(FactoryEntity factory);


    @Insert("INSERT INTO T_FACTORY_CONTACT (factoryId,contactId) VALUES (#{factoryId},#{contactid})")
    int createRelation(@Param(value="factoryId") String factoryId, @Param(value="contactid") String contactid);

    @SelectProvider(type=com.yidatec.mapper.FactoryQueryProvider.class,method = "selectFactory")
    List<FactoryEntity> selectFactoryList(FactoryVO factory);

    @SelectProvider(type=com.yidatec.mapper.FactoryQueryProvider.class,method = "countFactory")
    int countFactoryList(FactoryVO factory);

    @Select("SELECT * FROM T_FACTORY WHERE id = #{id}")
    FactoryEntity selectFactory(String id);

    @Select("SELECT * FROM T_FACTORY")
    List<FactoryEntity> getFactoryList();

    @Delete("DELETE FROM T_FACTORY_CONTACT  WHERE factoryId =#{factoryId}")
    int deleteFactoryRelation(String factoryId);

    @Insert("INSERT INTO T_FACTORY_CASE (factoryId,caseId) VALUES (#{factoryId},#{id})")
    int createCaseRelation(@Param(value="factoryId") String factoryId, @Param(value="id") String id);

    @Delete("DELETE FROM T_CONTACT  WHERE id in( SELECT contactId FROM T_FACTORY_CONTACT WHERE factoryId=#{factoryId})")
    int deleteContact(String customerId);

    @Select("SELECT * FROM T_CONTACT WHERE id in( SELECT contactId FROM T_FACTORY_CONTACT WHERE factoryId=#{factoryId})ORDER BY modifyTime DESC")
    List<Contact> getContact(String id);

    @Select("SELECT * FROM T_CONTACT A INNER JOIN T_FACTORY_CONTACT B ON A.id= B.contactId and B.factoryId = #{id}")
    List<Contact> selectContact(String id);

    @Delete("DELETE FROM T_FACTORY_CASE  WHERE factoryId =#{factoryId}")
    int deleteCaseRelation(String factoryId);


}
