package com.yidatec.mapper;


import com.yidatec.model.Audience;
import com.yidatec.model.User;
import com.yidatec.vo.AudienceVO;
import com.yidatec.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * jrw
 */
@Mapper
public interface AudienceMapper {

	/**
	 * 查找一个Audience
	 * @return
	 */
	@Select("SELECT a.*,b.campaignId FROM T_AUDIENCE a left join T_AUDIENCE_CAMPAIGN b on a.id = b.audienceId WHERE a.id = #{id}")
	Audience selectAudience(String id);

	@SelectProvider(type=AudienceQueryProvider.class,method = "selectAudienceList")
	List<Audience> selectAudienceList(AudienceVO audienceVO);
	@SelectProvider(type=AudienceQueryProvider.class,method = "countSelectAudienceList")
	int countSelectAudienceList(AudienceVO audienceVO);

	@Insert("INSERT INTO T_AUDIENCE (id,`name`,mobilePhone,email,country,province,city,region,hobby," +
			"creatorId,createTime,modifierId,modifyTime) VALUES (" +
			"#{id},#{name},#{mobilePhone},#{email},#{country},#{province},#{city},#{region},#{hobby}," +
			"#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int create(Audience audience);

	@Update("UPDATE T_AUDIENCE SET `name`=#{name},mobilePhone=#{mobilePhone},email=#{email},country=#{country},province=#{province},city=#{city},region=#{region},hobby=#{hobby}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(Audience audience);

	@Delete("DELETE FROM T_AUDIENCE WHERE id=#{id}")
	int delete(String id);

}
