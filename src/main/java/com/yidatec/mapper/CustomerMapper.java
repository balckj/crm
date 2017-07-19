package com.yidatec.mapper;

import com.yidatec.model.Customer;
import com.yidatec.vo.CustomerVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */
@Mapper
public interface CustomerMapper {
    /**
     * 载入客户可列表
     * @return
     */
    @Select("SELECT * FROM T_CUSTOMER WHERE id = #{id}")
    Customer selectCustomer(String id);

    @SelectProvider(type=com.yidatec.mapper.CustomerQueryProvider.class,method = "selectCustomer")
    List<Customer> selectCustomerList(CustomerVO dictionaryVO);

    @SelectProvider(type=com.yidatec.mapper.CustomerQueryProvider.class,method = "countCustomer")
    int countCustomerList(CustomerVO dictionaryVO);

    @Insert("INSERT INTO T_CUSTOMER (id,companyName,companyId,industry,nature,country,province,city,region,address,level,state,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{companyName},#{companyId}," +
            "#{industry},#{nature},#{country},#{province},#{city},#{region},#{address},#{level},#{state},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int create(Customer customer);

    @Update("UPDATE T_CUSTOMER SET `companyName`=#{companyName},companyId=#{companyId},industry=#{industry},nature=#{nature}," +
            "country=#{country},province=#{province},city=#{city},region=#{region},address=#{address},level=#{level},region=#{region},state=#{state}," +
            "modifierId=#{modifierId}," +
            "modifyTime=#{modifyTime} WHERE id=#{id}")
    int update(Customer customer);
}
