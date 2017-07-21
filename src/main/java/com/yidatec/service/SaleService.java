package com.yidatec.service;

import com.yidatec.mapper.SaleMapper;
import com.yidatec.model.Sale;
import com.yidatec.vo.SaleVO;
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

	public Sale selectSale(String id){
		return  saleMapper.selectSale(id);
	}

	/**
	 * 查询销售
	 *
	 * @return
	 */
	public List<Sale> selectSaleListByName(SaleVO SaleVO) {
		return saleMapper.selectSaleListByName(SaleVO);
	}

	/**
	 * 查询销售
	 *
	 * @return
	 */
	public int countSaleListByName(SaleVO SaleVO) {
		return saleMapper.countSaleListByName(SaleVO);
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
	public void createSale(Sale Sale) {
		saleMapper.create(Sale);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateSale(Sale Sale) {
		saleMapper.update(Sale);
	}
}
