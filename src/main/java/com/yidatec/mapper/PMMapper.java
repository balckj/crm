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

	@Insert("INSERT INTO T_USER (id,`referrer`,`name`,`nameEN`,mobilePhone,password,wechat,email,englishAbility,country,province,city,address,birthday,previous,experience,goodAtIndustry,goodAtArea,state," +
			"creatorId,createTime,modifierId,modifyTime) VALUES (" +
			"#{id},#{referrer},#{name},#{nameEN},#{mobilePhone},#{password},#{wechat},#{email},#{englishAbility},#{country},#{province},#{city},#{address},#{birthday},#{previous},#{experience},#{goodAtIndustry},#{goodAtArea},#{state}," +
			"#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int create(User user);

	@Update("UPDATE T_USER SET `referrer`=#{referrer},`name`=#{name},`nameEN`=#{nameEN},mobilePhone=#{mobilePhone},password=#{password},wechat=#{wechat},email=#{email},englishAbility=#{englishAbility},country=#{country},province=#{province},city=#{city},address=#{address},birthday=#{birthday},previous=#{previous},experience=#{experience},goodAtIndustry=#{goodAtIndustry},goodAtArea=#{goodAtArea}" +
			"state=#{state}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(User user);

	@Delete("DELETE FROM T_USER WHERE id=#{id}")
	int delete(String id);

}
