package com.yidatec.mapper;


import com.yidatec.model.Contract;
import com.yidatec.model.ProjectEntity;
import com.yidatec.vo.ABVO;
import com.yidatec.vo.ContractVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * jrw
 */
@Mapper
public interface ContractMapper {

	/**
	 * 查找一个合同
	 * @return
	 */
	@Select("SELECT A.*,A.paymentMethod as contractPaymentMethod,B.name as projectName,B.code as projectCode,C.name as campaignName FROM T_CONTRACT A " +
			" LEFT JOIN T_PROJECT B ON A.projectId = B.ID " +
			" LEFT JOIN T_CAMPAIGN C ON A.campaignId = C.ID" +
			" WHERE A.id = #{id}")
	ContractVO selectContract(String id);

	@SelectProvider(type=ContractQueryProvider.class,method = "selectContractList")
	List<ContractVO> selectContractList(ContractVO contractVO);
	@SelectProvider(type=ContractQueryProvider.class,method = "countContractList")
	int countContractList(ContractVO contractVO);

	// 载入甲乙合同Table
	@SelectProvider(type=ContractQueryProvider.class,method = "selectABVOTable")
	List<ABVO> selectABVOTable(ABVO abvo);
	@SelectProvider(type=ContractQueryProvider.class,method = "countABVOTable")
	int countABVOTable(ABVO abvo);

	@SelectProvider(type=ContractQueryProvider.class,method = "getABList")
	List<ABVO> getABList(ProjectEntity projectEntity);

	@Insert("INSERT INTO T_CONTRACT (id,`signDay`,`name`,`code`,projectId,campaignId," +
			"exhibitionNumber,area,amount,category,tax,paymentMethod,initialPaymentTime,middlePaymentTime,finalPaymentTime," +
			"billingInfo,firstParty,secondParty,remarks," +
			"creatorId,createTime,modifierId,modifyTime) VALUES (" +
			"#{id},#{signDay},#{name},#{code},#{projectId},#{campaignId}," +
			"#{exhibitionNumber},#{area},#{amount},#{category},#{tax},#{contractPaymentMethod},#{initialPaymentTime},#{middlePaymentTime},#{finalPaymentTime}," +
			"#{billingInfo},#{firstParty},#{secondParty},#{remarks}," +
			"#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int create(Contract contract);

	@Update("UPDATE T_CONTRACT SET " +
			"`signDay`=#{signDay},`name`=#{name},`code`=#{code},projectId=#{projectId},campaignId=#{campaignId},exhibitionNumber=#{exhibitionNumber}," +
			"area=#{area},amount=#{amount},category=#{category},tax=#{tax},paymentMethod=#{contractPaymentMethod}," +
			"initialPaymentTime=#{initialPaymentTime},middlePaymentTime=#{middlePaymentTime},finalPaymentTime=#{finalPaymentTime}," +
			"billingInfo=#{billingInfo},firstParty=#{firstParty},secondParty=#{secondParty},remarks=#{remarks}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(Contract contract);

	@Delete("DELETE FROM T_CONTRACT WHERE id=#{id}")
	int delete(String id);

}
