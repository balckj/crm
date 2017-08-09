package com.yidatec.mapper;

import com.yidatec.model.Contact;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
@Mapper
public interface ContactMapper {

    //新建联系人
    @Insert("INSERT INTO T_CONTACT (id,name,mobilePhone,position,email,state,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{name},#{mobilePhone},#{position}," +
            "#{email},#{state},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int createContact(Contact contact);

    //删除客户联系人
    @Delete("DELETE FROM T_CONTACT  WHERE id in( SELECT contactId FROM T_CUSTOMER_CONTACT WHERE customerId=#{customerId})")
    int deleteCustomerContact(String customerId);

    //删除工厂联系人
    @Delete("DELETE FROM T_CONTACT  WHERE id in( SELECT contactId FROM T_FACTORY_CONTACT WHERE factoryId=#{factoryId})")
    int deleteFactoryContact(String factoryId);

    @Select("SELECT * FROM T_CONTACT WHERE id in( SELECT contactId FROM T_CUSTOMER_CONTACT WHERE customerId=#{customerId} ORDER BY modifyTime DESC)")
    List<Contact> getContact(String id);

    @Select("SELECT * FROM T_CONTACT A INNER JOIN T_CUSTOMER_CONTACT B ON A.id= B.contactId and B.customerId = #{id}")
    List<Contact> selectContact(String id);





}
