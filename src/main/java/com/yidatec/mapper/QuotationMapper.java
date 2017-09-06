package com.yidatec.mapper;


import com.yidatec.model.Quotation;
import com.yidatec.vo.QuotationVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

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
	List<QuotationVO> quotationDownLoad();

	//新建报价单
	@Insert("INSERT INTO T_QUOTATION (id,projectId,priceLevel,productionId,unitPrice,count,workContent,remark,creatorId,createTime,modifierId,modifyTime) VALUES (#{id},#{projectId},#{priceLevel},#{productionId}," +
			"#{unitPrice},#{count},#{workContent},#{remark},#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int createQuotation(Quotation quotation);

	@SelectProvider(type=com.yidatec.mapper.QuotationQueryProvider.class,method = "selectQuotationList")
	List<Quotation> selectQuotationList(QuotationVO quotationVO);

	@SelectProvider(type=com.yidatec.mapper.QuotationQueryProvider.class,method = "countQuotationList")
	int countQuotationList(QuotationVO quotationVO);

}
