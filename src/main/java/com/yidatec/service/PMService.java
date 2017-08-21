package com.yidatec.service;

import com.yidatec.mapper.PMMapper;
import com.yidatec.mapper.RoleMapper;
import com.yidatec.mapper.UserRoleMapper;
import com.yidatec.model.Param;
import com.yidatec.model.User;
import com.yidatec.model.UserRole;
import com.yidatec.util.Constants;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 项目经理
 *
 * @author jrw
 *
 */
@Service("pMService")
public class PMService {

	@Autowired
	PMMapper pMMapper;

	@Autowired
	ParamService paramService;

	@Autowired
	UserRoleMapper userRoleMapper;

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	UserService userService;

	public User selectPM(String id){
		return  pMMapper.selectPM(id);
	}

	/**
	 * 查询PM
	 *
	 * @return
	 */
	public List<User> selectPMListByNameOrPhone(UserVO userVO) {
		Param param = paramService.findParam(Constants.PM_PARAM_ID);
		userVO.setParaRoleIDS(param.getValue());
		return  pMMapper.selectUserByRolePM(userVO);
	}

	public List<User> selectPMforProject(UserVO userVO) {
		Param param = paramService.findParam(Constants.PM_PARAM_ID);
		userVO.setParaRoleIDS(param.getValue());
		return  pMMapper.selectPMforProject(userVO);
	}

	/**
	 * 查询PM
	 *
	 * @return
	 */
	public int countPMListByNameOrPhone(UserVO userVO) {
		Param param = paramService.findParam(Constants.PM_PARAM_ID);
		userVO.setParaRoleIDS(param.getValue());
		return pMMapper.countUserByRolePM(userVO);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void deleteSale(String id) {
		pMMapper.delete(id);
	}


	/**
	 * 创建销售
	 *
	 * @param user
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createSale(User user) {
		insertUserRole(user);
		pMMapper.create(user);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateSale(User user) {
		pMMapper.update(user);
	}

	private void insertUserRole(User user){
		Param param = paramService.findParam(Constants.PM_PARAM_ID);
		if(!StringUtils.isEmpty(param.getValue())){
			String[] valus = param.getValue().split(",");
			for (String s : valus){
				UserRole userRole = new UserRole();
				userRole.setUserId(user.getId());
				userRole.setRoleId(s);
				userRoleMapper.insertUserRole(userRole);
			}
		}
	}
}
