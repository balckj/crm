package com.yidatec.mapper;


import com.yidatec.model.User;
import com.yidatec.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * jrw
 */
@Mapper
public interface PMMapper {

	/**
	 * 查找一个PM
	 * @return
	 */
	@Select("SELECT * FROM T_USER WHERE id = #{id}")
	User selectPM(String id);


	@SelectProvider(type=com.yidatec.mapper.PMQueryProvider.class,method = "selectUserByRolePM")
	List<User> selectUserByRolePM(UserVO userVO);
	@SelectProvider(type=com.yidatec.mapper.PMQueryProvider.class,method = "countUserByRolePM")
	int countUserByRolePM(UserVO userVO);

	@SelectProvider(type=com.yidatec.mapper.PMQueryProvider.class,method = "selectPMforProject")
	List<User> selectPMforProject(UserVO userVO);

	@Insert("INSERT INTO T_USER (id,`referrer`,`name`,`nameEN`,mobilePhone,password,wechat,email,englishAbility,country,province,city,address,region,birthday,previous,experience,goodAtIndustry,goodAtArea,platformLevel,state," +
			"creatorId,createTime,modifierId,modifyTime) VALUES (" +
			"#{id},#{referrer},#{name},#{nameEN},#{mobilePhone},#{password},#{wechat},#{email},#{englishAbility},#{country},#{province},#{city},#{address},#{region},#{birthday},#{previous},#{experience},#{goodAtIndustry},#{goodAtArea},#{platformLevel},#{state}," +
			"#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int create(User user);

	@Update("UPDATE T_USER SET `referrer`=#{referrer},`name`=#{name},`nameEN`=#{nameEN},mobilePhone=#{mobilePhone},password=#{password},wechat=#{wechat},email=#{email},englishAbility=#{englishAbility},country=#{country},province=#{province},city=#{city},address=#{address},region=#{region},birthday=#{birthday},previous=#{previous},experience=#{experience},goodAtIndustry=#{goodAtIndustry},goodAtArea=#{goodAtArea},platformLevel=#{platformLevel}," +
			"state=#{state}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(User user);

	@Delete("DELETE FROM T_USER WHERE id=#{id}")
	int delete(String id);

}
