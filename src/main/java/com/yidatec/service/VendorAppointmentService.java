package com.yidatec.service;

import com.yidatec.mapper.VendorAppointmentMapper;
import com.yidatec.model.VendorAppointment;
import com.yidatec.vo.VendorAppointmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 供应商空闲时间维护
 *
 * @author jrw
 *
 */
@Service("vendorAppointmentService")
public class VendorAppointmentService {

	@Autowired
	VendorAppointmentMapper vendorAppointmentMapper;

	/**
	 * 查询 供应商空闲时间
	 *
	 * @return
	 */
	public List<VendorAppointment> selectVendorAppointmentList(VendorAppointmentVO vendorAppointmentVO) {
		return vendorAppointmentMapper.selectVendorAppointmentList(vendorAppointmentVO);
	}

	/**
	 * 查询 供应商空闲时间
	 *
	 * @param vendorAppointment
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createVendorAppointment(VendorAppointment vendorAppointment) {
		vendorAppointmentMapper.delete(vendorAppointment.getId());
		vendorAppointmentMapper.create(vendorAppointment);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void deleteVendorAppointment(String id) {
		vendorAppointmentMapper.delete(id);
	}

//	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
//	public void updateVendorAppointment(VendorAppointment vendorAppointment) {
//		vendorAppointmentMapper.update(vendorAppointment);
//	}
}
