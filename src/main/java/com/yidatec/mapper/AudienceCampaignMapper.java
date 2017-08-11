package com.yidatec.mapper;

import com.yidatec.model.AudienceCampaign;
import com.yidatec.model.Case;
import com.yidatec.model.UserCase;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jrw on 2017/8/3.
 */
@Mapper
public interface AudienceCampaignMapper {

    @Insert("INSERT INTO T_AUDIENCE_CAMPAIGN (audienceId,campaignId) VALUES (#{audienceId},#{campaignId})")
    int createAudienceCampaign(AudienceCampaign audienceCampaign);

    @Delete("DELETE FROM T_AUDIENCE_CAMPAIGN WHERE audienceId=#{audienceId}")
    int deleteAudienceCampaign(String designerId);

}
