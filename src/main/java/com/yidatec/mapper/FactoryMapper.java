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

    @Insert("INSERT INTO T_FACTORY (id,referrer,factoryName,director,firstOrderTime,country,province,city,region,address,factoryArea,photo,registeredCapital,taxpayerType,fixedEmployeeCount,goodAtIndustry,goodAtMaterial,goodAtArea,platformLevel,valueAddedTaxAccount,taxNumber,state,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{referrer},#{factoryName}," +
            "#{director},#{firstOrderTime},#{country},#{province},#{city},#{region},#{address},#{factoryArea},#{photo},#{registeredCapital},#{taxpayerType},#{fixedEmployeeCount},#{goodAtIndustry},#{goodAtMaterial},#{goodAtArea},#{platformLevel},#{valueAddedTaxAccount},#{taxNumber},#{state},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int create(FactoryEntity factory);

    @Update("UPDATE T_CUSTOMER SET `companyName`=#{companyName},companyId=#{companyId},industry=#{industry},nature=#{nature}," +
            "country=#{country},province=#{province},city=#{city},region=#{region},address=#{address},level=#{level},region=#{region},state=#{state}," +
            "modifierId=#{modifierId}," +
            "modifyTime=#{modifyTime} WHERE id=#{id}")
    int update(FactoryEntity factory);

    @Insert("INSERT INTO T_CONTACT (id,name,mobilePhone,position,email,state,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{name},#{mobilePhone},#{position}," +
            "#{email},#{state},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int createContact(Contact contact);

    @Insert("INSERT INTO T_FACTORY_CONTACT (factoryId,contactId) VALUES (#{factoryId},#{contactid})")
    int createRelation(@Param(value="factoryId") String factoryId, @Param(value="contactid") String contactid);

    @SelectProvider(type=com.yidatec.mapper.FactoryQueryProvider.class,method = "selectFactory")
    List<FactoryEntity> selectFactoryList(FactoryVO factory);

    @SelectProvider(type=com.yidatec.mapper.FactoryQueryProvider.class,method = "countFactory")
    int countFactoryList(FactoryVO factory);

    @Select("SELECT * FROM T_FACTORY WHERE id = #{id}")
    FactoryEntity selectFactory(String id);
}
