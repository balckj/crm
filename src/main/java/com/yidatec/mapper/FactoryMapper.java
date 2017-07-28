package com.yidatec.mapper;

import com.yidatec.model.Contact;
import com.yidatec.model.FactoryEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Administrator on 2017/7/27.
 */
@Mapper
public interface FactoryMapper {

    @Insert("INSERT INTO T_CUSTOMER (id,companyName,companyId,industry,nature,country,province,city,region,address,level,state,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{companyName},#{companyId}," +
            "#{industry},#{nature},#{country},#{province},#{city},#{region},#{address},#{level},#{state},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int create(FactoryEntity factory);

    @Update("UPDATE T_CUSTOMER SET `companyName`=#{companyName},companyId=#{companyId},industry=#{industry},nature=#{nature}," +
            "country=#{country},province=#{province},city=#{city},region=#{region},address=#{address},level=#{level},region=#{region},state=#{state}," +
            "modifierId=#{modifierId}," +
            "modifyTime=#{modifyTime} WHERE id=#{id}")
    int update(FactoryEntity factory);

    @Insert("INSERT INTO T_CONTACT (id,name,mobilePhone,position,email,state,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{name},#{mobilePhone},#{position}," +
            "#{email},#{state},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int createContact(Contact contact);
}
