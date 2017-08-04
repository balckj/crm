package com.yidatec.mapper;

import com.yidatec.model.Contact;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2017/8/3.
 */
@Mapper
public interface ContactMapper {

    @Insert("INSERT INTO T_CONTACT (id,name,mobilePhone,position,email,state,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{name},#{mobilePhone},#{position}," +
            "#{email},#{state},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int createContact(Contact contact);





}
