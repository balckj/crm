package com.yidatec.service;

import com.yidatec.mapper.*;
import com.yidatec.model.*;
import com.yidatec.util.Constants;
import com.yidatec.vo.AudienceVO;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * 观众
 *
 * @author jrw
 *
 */
@Service("audienceService")
public class AudienceService {

	@Autowired
	AudienceMapper audienceMapper;

	@Autowired
	AudienceCampaignMapper audienceCampaignMapper;

	public Audience selectAudience(String id){
		return  audienceMapper.selectAudience(id);
	}

	/**
	 * 查询观众list
	 *
	 * @return
	 */
	public List<Audience> selectAudienceList(AudienceVO Audience) {
		return  audienceMapper.selectAudienceList(Audience);
	}

	/**
	 * 查询观众
	 *
	 * @return
	 */
	public int countSelectAudienceList(AudienceVO audience) {
		return audienceMapper.countSelectAudienceList(audience);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void deleteAudience(String id) {
		audienceMapper.delete(id);
	}


	/**
	 * 创建一个观众
	 *
	 * @param audience
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createAudience(Audience audience) {
		audienceMapper.create(audience);
		createAudienceCampaign(audience);
	}



	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateAudience(Audience audience) {
		audienceCampaignMapper.deleteAudienceCampaign(audience.getId());
		audienceMapper.update(audience);
		createAudienceCampaign(audience);

	}

	/**
	 * 观众和活动关系表操作
	 */
	private void createAudienceCampaign(Audience audience) {
		if(audience.getCampaignName() != null){
			String[] campaign = audience.getCampaignName().split(",");
			for (String c : campaign) {
				AudienceCampaign audienceCampaign = new AudienceCampaign();
				audienceCampaign.setAudienceId(audience.getId());
				audienceCampaign.setCampaignId(c);
				audienceCampaignMapper.createAudienceCampaign(audienceCampaign);
			}
		}
	}

	// 上传文件批量导入
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createAudienceList(List<Audience> audienceList) {
		if (audienceList != null){
			for (int i = 0; i < audienceList.size(); i++) {
				audienceMapper.create(audienceList.get(i));
			}
		}
	}

}
