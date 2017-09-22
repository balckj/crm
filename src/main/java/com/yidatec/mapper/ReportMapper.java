package com.yidatec.mapper;


import com.yidatec.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jrw on 2017/7/21.
 */
@Mapper
public interface ReportMapper {

//    @SelectProvider(type=AchievementReportQueryProvider.class,method = "selectAchievementReportVOList")
    @Select("SELECT\n" +
            "\tp.id AS projectId,\n" +
            "\tp.pmId AS pmId,\n" +
            "\tp.developSaleId AS developSaleId,\n" +
            "\tp.traceSaleId AS traceSaleId,\n" +
            "\tc.id AS contractId,\n" +
            "\tc.`code` AS contractCode,\n" +
            "\tc.`secondParty` AS contractSecondParty,\n" +
            "\tc.`category` AS contractCategory,\n" +
            "\tp.`name` AS projectName,\n" +
            "\tcus.`name` AS customerName,\n" +
            "\tcus.`source` AS customerSource,\n" +
            "\tca.`name` AS campaignName,\n" +
            "\tca.`type` AS campaignType,\n" +
            "\tCONCAT( ca.startDate, '~', ca.endDate ) AS campaignStartEndTime,\n" +
            "\tp.exhibitionNumber AS exhibitionNumber,\n" +
            "\tc.`amount` AS contractCountAmount,\n" +
            "\tp.`area` AS projectArea,\n" +
            "\tCONCAT( IF(ca.`city` = '未选择','',ca.`city`) , '', ca.`address`  ) AS address,\n" +
            "\tIF(HIS_TOP.amount IS NULL,'-',c.amount - HIS_TOP.amount) AS contractCountAmountChange,\n" +
            "\tc.`creatorId` AS contractCreatorId,\n" +
            "\tp.`creatorId` AS projectCreatorId,\n" +
            "\tcus.`creatorId` AS customerCreatorId\n" +
            "FROM\n" +
            "\tT_PROJECT p\n" +
            "LEFT JOIN T_CUSTOMER cus ON p.customerId = cus.id\n" +
            "LEFT JOIN T_CAMPAIGN ca ON p.campaignId = ca.id\n" +
            "LEFT JOIN T_CONTRACT c ON p.id = c.projectId\n" +
            "\n" +
            "LEFT JOIN \n" +
            "(SELECT * FROM T_CONTRACT_HISTORY \n" +
            "    ORDER BY modifyTime ASC LIMIT 1) HIS_TOP ON c.id = HIS_TOP.id\n" +
            "WHERE p.createTime BETWEEN DATE(#{startTime}) AND DATE(#{endTime})\n" +
            "ORDER BY\n" +
            "\tp.modifyTime DESC")
    List<PerformanceReportVO> selectPerformanceReportVOBaseList(@Param(value = "startTime") String startTime, @Param(value = "endTime")String endTime);

    @Select("SELECT * FROM T_LEDGER L LEFT JOIN T_CONTRACT_LEDGER CL ON L.id = CL.ledgerId ORDER BY CL.contractId,L.modifyTime DESC")
    List<LedgerReportVO> selectLedgerReportList();//此处可以给Ledger表加合同的createTime字段来做性能提升



//    @Select("SELECT * FROM T_CONTRACT_HISTORY order by id, modifyTime ASC")
//    List<LedgerVO> getLedgerList(String contractId);

    @Select("SELECT * FROM T_PROJECT P1 LEFT JOIN  T_PROJECT_DESIGNER P  ON P1.id = P.projectid  LEFT JOIN T_USER U ON P.designerId = U.id WHERE P1.createTime BETWEEN DATE(#{startTime}) AND DATE(#{endTime})")
    List<DesignerReportVO> selectProjectDesigner(@Param(value = "startTime") String startTime, @Param(value = "endTime")String endTime);

    @Select("SELECT * FROM T_PROJECT P1 LEFT JOIN T_PROJECT_FACTORY P ON P1.id = P.projectid LEFT JOIN T_FACTORY U ON P.factoryId = U.id WHERE P1.createTime BETWEEN DATE(#{startTime}) AND DATE(#{endTime})")
    List<FactoryReportVO> selectProjectFactory(@Param(value = "startTime") String startTime, @Param(value = "endTime")String endTime);

}
