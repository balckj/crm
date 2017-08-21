package com.yidatec.service;

import com.yidatec.mapper.*;
import com.yidatec.model.*;
import com.yidatec.util.Constants;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * 设计师
 *
 * @author jrw
 *
 */
@Service("designerService")
public class DesignerService {

	@Autowired
	DesignerMapper designerMapper;

	@Autowired
	ParamService paramService;

	@Autowired
	UserRoleMapper userRoleMapper;

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	UserService userService;

	@Autowired
	CaseMapper caseMapper;

	@Autowired
	UserCaseMapper userCaseMapper;

	public User selectDesigner(String id){
		return  designerMapper.selectDesigner(id);
	}

	/**
	 * 查询Designer
	 *
	 * @return
	 */
	public List<User> selectDesignerListByNameOrPhone(UserVO userVO) {
		Param param = paramService.findParam(Constants.DESIGNER_PARAM_ID);
		userVO.setParaRoleIDS(param.getValue());
		return  designerMapper.selectUserByRoleDesigner(userVO);
	}

	public List<User> selectDesignerforProject(UserVO userVO) {
		Param param = paramService.findParam(Constants.DESIGNER_PARAM_ID);
		userVO.setParaRoleIDS(param.getValue());
		return  designerMapper.selectDesignerforProject(userVO);
	}

	/**
	 * 查询Designer
	 *
	 * @return
	 */
	public int countDesignerListByNameOrPhone(UserVO userVO) {
		Param param = paramService.findParam(Constants.DESIGNER_PARAM_ID);
		userVO.setParaRoleIDS(param.getValue());
		return designerMapper.countUserByRoleDesigner(userVO);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void deleteDesigner(String id) {
		designerMapper.delete(id);
	}


	/**
	 * 创建设计师
	 *
	 * @param user
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createDesigner(User user) {
		insertUserRole(user);
		designerMapper.create(user);
		caseAndUserCreate(user);

	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateDesigner(User user) {
		designerMapper.update(user);
		caseMapper.deleteCaseForDesigner(user.getId());
		userCaseMapper.deleteUserCase(user.getId());
		caseAndUserCreate(user);
	}

	private void caseAndUserCreate(User user) {
		if (user.getCaseList() != null){
			for (int i = 0; i < user.getCaseList().size(); i ++){
				Case aCase = user.getCaseList().get(i);
				if(StringUtils.isEmpty(aCase.getName()) || StringUtils.isEmpty(aCase.getPhoto()) ){
					continue;
				}
				String caseId = UUID.randomUUID().toString().toLowerCase();
				aCase.setId(caseId);
				aCase.setType(2);
				caseMapper.createCase(aCase);
				UserCase userCase = new UserCase();
				userCase.setDesignerId(user.getId());
				userCase.setCaseId(caseId);
				userCaseMapper.createUserCase(userCase);
			}
		}
	}

	private void insertUserRole(User user){
		Param param = paramService.findParam(Constants.DESIGNER_PARAM_ID);
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
