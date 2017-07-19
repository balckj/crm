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
	SaleMapper SaleMapper;

	public Sale selectSale(String id){
		return  SaleMapper.selectSale(id);
	}

	/**
	 * 查询销售
	 *
	 * @return
	 */
	public List<Sale> selectSaleListByName(SaleVO SaleVO) {
		return SaleMapper.selectSaleListByName(SaleVO);
	}

	/**
	 * 查询销售
	 *
	 * @return
	 */
	public int countSaleListByName(SaleVO SaleVO) {
		return SaleMapper.countSaleListByName(SaleVO);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void deleteSale(String id) {
		SaleMapper.delete(id);
	}

	/**
	 * 查询销售
	 *
	 * @param Sale
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createSale(Sale Sale) {
		SaleMapper.create(Sale);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateSale(Sale Sale) {
		SaleMapper.update(Sale);
	}
}
