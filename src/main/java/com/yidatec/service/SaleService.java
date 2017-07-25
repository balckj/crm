package com.yidatec.service;

import com.yidatec.mapper.SaleMapper;
import com.yidatec.model.Sale;
import com.yidatec.model.User;
import com.yidatec.vo.SaleVO;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	public User selectSale(String id){
		return  saleMapper.selectSale(id);
	}

	/**
	 * 查询销售
	 *
	 * @return
	 */
	public List<User> selectSaleListByName(UserVO userVO) {
		return saleMapper.selectSaleListByName(userVO);
	}

	/**
	 * 查询销售
	 *
	 * @return
	 */
	public int countSaleListByName(UserVO userVO) {
		return saleMapper.countSaleListByName(userVO);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void deleteSale(String id) {
		saleMapper.delete(id);
	}

	/**
	 * 创建销售
	 *
	 * @param Sale
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createSale(User user) {
		saleMapper.create(user);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateSale(User user) {
		saleMapper.update(user);
	}
}
