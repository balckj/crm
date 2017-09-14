package com.yidatec.service;

import com.yidatec.mapper.RoleMapper;
import com.yidatec.mapper.SaleMapper;
import com.yidatec.mapper.UserRoleMapper;
import com.yidatec.model.*;
import com.yidatec.util.Constants;
import com.yidatec.vo.SaleVO;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 销售
 *
 * @author jrw
 *
 */
@Service("saleService")
public class SaleService {

	@Autowired
	SaleMapper saleMapper;

	@Autowired
	ParamService paramService;

	@Autowired
	UserRoleMapper userRoleMapper;

	@Autowired
	RoleMapper roleMapper;

	public User selectSale(String id){
		return  saleMapper.selectSale(id);
	}

	/**
	 * 查询销售
	 *
	 * @return
	 */
	public List<User> selectSaleListByName(UserVO userVO) {
		Param param = paramService.findParam(Constants.SALE_PARAM_ID);
		userVO.setParaRoleIDS(param.getValue());
		return  roleMapper.selectUserByRoleCommon(userVO);
	}

	public List<User> selectSaleListforProject(UserVO userVO) {
		Param param = paramService.findParam(Constants.SALE_PARAM_ID);
		userVO.setParaRoleIDS(param.getValue());
		return  roleMapper.selectSaleListALL(userVO);
	}

	/**
	 * 查询销售
	 *
	 * @return
	 */
	public int countSaleListByName(UserVO userVO) {
		Param param = paramService.findParam(Constants.SALE_PARAM_ID);
		userVO.setParaRoleIDS(param.getValue());
		return roleMapper.countUserByRoleCommon(userVO);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void deleteSale(String id) {
		saleMapper.delete(id);
	}


	/**
	 * 查询所有销售
	 *
	 * @return
	 */
	public List<User> selectSaleListALL(UserVO userVO) {
		Param param = paramService.findParam(Constants.SALE_PARAM_ID);
		userVO.setParaRoleIDS(param.getValue());
		return  roleMapper.selectSaleListALL(userVO);
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
		saleMapper.create(user);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateSale(User user) {
//		userRoleMapper.deleteUserRole(Constants.SALE_PARAM_ID);// 更新的时候先删除再更新角色表
//		insertUserRole(user);
		saleMapper.update(user);
	}

	private void insertUserRole(User user){
		Param param = paramService.findParam(Constants.SALE_PARAM_ID);
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
