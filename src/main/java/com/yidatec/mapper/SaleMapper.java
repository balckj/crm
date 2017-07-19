package com.yidatec.mapper;


import com.yidatec.model.Sale;
import com.yidatec.vo.SaleVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * jrw
 */
@Mapper
public interface SaleMapper {

	/**
	 * 查找一个供应商
	 * @return
	 */
	@Select("SELECT * FROM T_SALE WHERE id = #{id}")
	Sale selectSale(String id);

	/**
	 * 供应商列表
	 * @param SaleVO
	 * @return
	 */
	@SelectProvider(type=com.yidatec.mapper.SaleQueryProvider.class,method = "selectSale")
	List<Sale> selectSaleListByName(SaleVO SaleVO);
	/**
	 * 供应商列表
	 * @param SaleVO
	 * @return
	 */
	@SelectProvider(type=com.yidatec.mapper.SaleQueryProvider.class,method = "countSale")
	int countSaleListByName(SaleVO SaleVO);


	@Insert("INSERT INTO T_SALE (id,`name`,channel,mobilePhone,email,state,creatorId,createTime," +
			"modifierId,modifyTime) VALUES (#{id},#{name}," +
			"#{channel},#{mobilePhone},#{email},#{state}," +
			"#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int create(Sale Sale);

	@Update("UPDATE T_SALE SET `name`=#{name},channel=#{channel},mobilePhone=#{mobilePhone},email=#{email}," +
			"state=#{state}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(Sale Sale);

	@Delete("DELETE FROM T_SALE WHERE id=#{id}")
	int delete(String id);

}
