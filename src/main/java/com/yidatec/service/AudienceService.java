package com.yidatec.service;

import com.yidatec.mapper.AudienceMapper;
import com.yidatec.mapper.RoleMapper;
import com.yidatec.mapper.SaleMapper;
import com.yidatec.mapper.UserRoleMapper;
import com.yidatec.model.Param;
import com.yidatec.model.Audience;
import com.yidatec.model.User;
import com.yidatec.model.UserRole;
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
	public int countSelectAudienceList(AudienceVO Audience) {
		return audienceMapper.countSelectAudienceList(Audience);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void deleteAudience(String id) {
		audienceMapper.delete(id);
	}


	/**
	 * 创建一个观众
	 *
	 * @param Audience
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createAudience(Audience Audience) {
		audienceMapper.create(Audience);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateAudience(Audience Audience) {
		audienceMapper.update(Audience);
	}

}
