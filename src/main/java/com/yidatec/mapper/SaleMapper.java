package com.yidatec.mapper;


import com.yidatec.model.Sale;
import com.yidatec.model.User;
import com.yidatec.vo.SaleVO;
import com.yidatec.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * jrw
 */
@Mapper
public interface SaleMapper {

	/**
	 * 查找一个销售
	 * @return
	 */
	@Select("SELECT * FROM T_USER WHERE id = #{id}")
	User selectSale(String id);

	/**
	 * 销售列表
	 * @param userVO
	 * @return
	 */
	@SelectProvider(type=com.yidatec.mapper.SaleQueryProvider.class,method = "selectSale")
	List<User> selectSaleListByName(UserVO userVO);
	/**
	 * 销售列表
	 * @param userVO
	 * @return
	 */
	@SelectProvider(type=com.yidatec.mapper.SaleQueryProvider.class,method = "countSale")
	int countSaleListByName(UserVO userVO);


	@Insert("INSERT INTO T_USER (id,`name`,channel,mobilePhone,password,email,state,creatorId,createTime," +
			"modifierId,modifyTime) VALUES (#{id},#{name}," +
			"#{channel},#{mobilePhone},#{password},#{email},#{state}," +
			"#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int create(User user);

	@Update("UPDATE T_USER SET `name`=#{name},channel=#{channel},mobilePhone=#{mobilePhone},password=#{password},email=#{email}," +
			"state=#{state}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(User user);

	@Delete("DELETE FROM T_USER WHERE id=#{id}")
	int delete(String id);

}