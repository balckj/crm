package com.yidatec.mapper;

import com.yidatec.model.Customer;
import com.yidatec.vo.CustomerVO;
import com.yidatec.vo.ProjectVO;
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

    @Select("SELECT * FROM T_CUSTOMER")
    List<Customer> selectCustomerAll();


    @SelectProvider(type=com.yidatec.mapper.CustomerQueryProvider.class,method = "selectCustomer")
    List<Customer> selectCustomerList(CustomerVO customerVO);

    @SelectProvider(type=com.yidatec.mapper.CustomerQueryProvider.class,method = "countCustomer")
    int countCustomerList(CustomerVO customerVO);

    @Insert("INSERT INTO T_CUSTOMER (id,source,name,industry,nature,country,province,city,region,address,level,state,photo,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{source},#{name}," +
            "#{industry},#{nature},#{country},#{province},#{city},#{region},#{address},#{level},#{state},#{photo},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int create(Customer customer);

    @Update("UPDATE T_CUSTOMER SET `name`=#{name},source=#{source},industry=#{industry},nature=#{nature}," +
            "country=#{country},province=#{province},city=#{city},region=#{region},address=#{address},level=#{level},state=#{state},photo=#{photo}," +
            "modifierId=#{modifierId}," +
            "modifyTime=#{modifyTime} WHERE id=#{id}")
    int update(Customer customer);

    @Insert("INSERT INTO T_CUSTOMER_CONTACT (customerId,contactId) VALUES (#{customerid},#{contactid})")
    int createRelation(@Param(value="customerid") String customerid,@Param(value="contactid") String contactid);


    @Delete("DELETE FROM T_CUSTOMER_CONTACT  WHERE customerId =#{customerId}")
    int deleteCustomerRelation(String customerId);

    /**
     * 客户大表下载
     * @return
     */
    @SelectProvider(type=CustomerQueryProvider.class,method = "customerDownLoad")
    List<ProjectVO> customerDownLoad(String startTime,String endTime);

}
