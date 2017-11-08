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

    @Select("SELECT * FROM T_CUSTOMER WHERE name = #{name}")
    Customer getCustomer(String name);

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
    @Select("SELECT a.id,c.`name` as saleName,b.`name` as customerName,d.`value` as industry," +
            "h.`name` as campaignName,b.country,b.province,b.city,a.exhibitionNumber," +
            "f.`name` as userName,f.mobilePhone,g.value as position,f.email,a.remark,a.createTime" +
            " FROM `T_PROJECT` a" +
            " LEFT JOIN T_CUSTOMER b ON a.customerId = b.id" +
            " LEFT JOIN T_CAMPAIGN h ON a.campaignId = h.id" +
            " LEFT JOIN T_CUSTOMER_CONTACT e ON e.customerId = b.id"+
            " LEFT JOIN T_CONTACT f ON e.contactId = f.id"+
            " LEFT JOIN T_DICTIONARY g ON f.position = g.id"+
            " LEFT JOIN T_USER c ON a.developSaleId = c.id"+
            " LEFT JOIN T_DICTIONARY d ON b.industry = d.id"+
            " where a.createTime BETWEEN DATE(#{startTime}) AND DATE(#{endTime})"+
            " ORDER BY a.createTime ")
    List<ProjectVO> customerDownLoad(@Param(value = "startTime") String startTime, @Param(value = "endTime")String endTime);

}
