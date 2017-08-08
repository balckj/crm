package com.yidatec.service;

import com.yidatec.mapper.DesignerMapper;
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
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateDesigner(User user) {
		designerMapper.update(user);
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
