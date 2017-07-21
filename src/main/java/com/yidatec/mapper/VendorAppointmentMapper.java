package com.yidatec.mapper;


import com.yidatec.model.VendorAppointment;
import com.yidatec.vo.VendorAppointmentVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * jrw
 */
@Mapper
public interface VendorAppointmentMapper {

	/**
	 * 查找一个供应商空闲时间
	 * @return
	 */
	@Select("SELECT * FROM T_VENDOR_APPOINTMENT WHERE id = #{id}")
	VendorAppointment selectVendorAppointment(String id);
	
	
	/**
	 * 供应商空闲时间列表
	 * @param vendorAppointmentVO
	 * @return
	 */
	@Select("SELECT * FROM T_VENDOR_APPOINTMENT WHERE vendorId=#{vendorId} AND DATE(start) BETWEEN DATE(#{start}) AND DATE(#{end}) ORDER BY start ASC")
	List<VendorAppointment> selectVendorAppointmentList(VendorAppointmentVO vendorAppointmentVO);

	@Insert("INSERT INTO T_VENDOR_APPOINTMENT (id,`vendorId`,title,allDay,start,end,type,creatorId,createTime," +
			"modifierId,modifyTime) VALUES (#{id},#{vendorId}," +
			"#{title},#{allDay},#{start},#{end},#{type}," +
			"#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int create(VendorAppointment vendorAppointment);

	@Update("UPDATE T_VENDOR_APPOINTMENT SET `vendorId`=#{vendorId},title=#{title},allDay=#{allDay},start=#{start}," +
			"end=#{end}," +
			"type=#{type}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(VendorAppointment vendorAppointment);

	@Delete("DELETE FROM T_VENDOR_APPOINTMENT WHERE id=#{id}")
	int delete(String id);

}
