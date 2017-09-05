package com.yidatec.mapper;


import com.yidatec.model.Audience;
import com.yidatec.vo.AudienceVO;
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
	List<QuotationVO> quotationDownLoad();

}
