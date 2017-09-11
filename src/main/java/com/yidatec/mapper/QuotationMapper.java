package com.yidatec.mapper;


import com.yidatec.model.Quotation;
import com.yidatec.vo.QuotationVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * jrw
 */
@Mapper
public interface QuotationMapper {

	/**
	 * 报价单下载
	 * @return
	 */
	@SelectProvider(type=QuotationQueryProvider.class,method = "quotationDownLoad")
	List<QuotationVO> quotationDownLoad(String id);

	//新建报价单
	@Insert("INSERT INTO T_QUOTATION (id,projectId,priceLevel,remark,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{projectId},#{priceLevel}," +
			"#{remark},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int createQuotation(Quotation quotation);

	//新建报价单
	@Insert("INSERT INTO T_QUOTATION_PRODUCTION (qoutationId,productionId,unitPrice,count,workContent) VALUES (#{id},#{productionId},#{unitPrice},#{count}," +
			"#{workContent})")
	int createQuotationProduction(QuotationVO quotation);

	@Update("UPDATE T_QUOTATION SET projectId=#{projectId},priceLevel=#{priceLevel},remark=#{remark}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(QuotationVO quotation);


	@SelectProvider(type=com.yidatec.mapper.QuotationQueryProvider.class,method = "selectQuotationList")
	List<QuotationVO> selectQuotationList(QuotationVO quotationVO);

	@SelectProvider(type=com.yidatec.mapper.QuotationQueryProvider.class,method = "countQuotationList")
	int countQuotationList(QuotationVO quotationVO);

	@Select("SELECT * FROM T_QUOTATION WHERE id = #{id}")
	QuotationVO selectQuotation(String id);

	@Select("SELECT * FROM T_QUOTATION_PRODUCTION WHERE qoutationId = #{id}")
	List<QuotationVO> selectProduction(String id);

	@Delete("DELETE FROM T_QUOTATION_PRODUCTION WHERE qoutationId= #{id}")
	int deleteQuotationProduction(String id);


}
