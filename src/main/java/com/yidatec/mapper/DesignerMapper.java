package com.yidatec.mapper;


import com.yidatec.model.User;
import com.yidatec.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * jrw
 */
@Mapper
public interface DesignerMapper {

	/**
	 * 查找一个Designer
	 * @return
	 */
	@Select("SELECT * FROM T_USER WHERE id = #{id}")
	User selectDesigner(String id);


	@SelectProvider(type=DesignerQueryProvider.class,method = "selectUserByRoleDesigner")
	List<User> selectUserByRoleDesigner(UserVO userVO);
	@SelectProvider(type=DesignerQueryProvider.class,method = "countUserByRoleDesigner")
	int countUserByRoleDesigner(UserVO userVO);

	@SelectProvider(type=DesignerQueryProvider.class,method = "selectDesignerforProject")
	List<User> selectDesignerforProject(UserVO userVO);

	@Insert("INSERT INTO T_USER (id,`referrer`,`name`,`nameEN`,`designerCategory`,mobilePhone,password,wechat,email,englishAbility,country,province,city,address,region,birthday,previous,experience,goodAtIndustry,goodAtArea,designStyle,gender,platformLevel,platformCreditLevel,state," +
			"creatorId,createTime,modifierId,modifyTime) VALUES (" +
			"#{id},#{referrer},#{name},#{nameEN},#{designerCategory},#{mobilePhone},#{password},#{wechat},#{email},#{englishAbility},#{country},#{province},#{city},#{address},#{region},#{birthday},#{previous},#{experience},#{goodAtIndustry},#{goodAtArea},#{designStyle},#{gender},#{platformLevel},#{platformCreditLevel},#{state}," +
			"#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int create(User user);

	@Update("UPDATE T_USER SET `referrer`=#{referrer},`name`=#{name},`nameEN`=#{nameEN},`designerCategory`=#{designerCategory},mobilePhone=#{mobilePhone},password=#{password},wechat=#{wechat},email=#{email},englishAbility=#{englishAbility},country=#{country},province=#{province},city=#{city},address=#{address},region=#{region},birthday=#{birthday},previous=#{previous},experience=#{experience},goodAtIndustry=#{goodAtIndustry},goodAtArea=#{goodAtArea},designStyle=#{designStyle},gender=#{gender},platformLevel=#{platformLevel},platformCreditLevel=#{platformCreditLevel}," +
			"state=#{state}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(User user);

	@Delete("DELETE FROM T_USER WHERE id=#{id}")
	int delete(String id);

}
