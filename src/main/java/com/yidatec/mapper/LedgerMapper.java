package com.yidatec.mapper;

import com.yidatec.model.Ledger;
import com.yidatec.vo.LedgerVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jrw on 2017/8/3.
 */
@Mapper
public interface LedgerMapper {

    @Insert("INSERT INTO T_LEDGER (id,category,paymentMethod,costCenter,paymentTime,paymentAmount,operator,reasonForChange," +
            "creatorId,createTime,modifierId,modifyTime) " +
            "VALUES (#{id},#{moneyType},#{paymentMethod},#{costCenter},#{paymentTime},#{paymentAmount},#{operator},#{reasonForChange}," +
            "#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
    int createLedger(Ledger ledger);

    @Delete("DELETE FROM T_LEDGER  WHERE id in (SELECT ledgerId FROM T_CONTRACT_LEDGER WHERE contractId=#{contractId})")
    int deleteLedger(String contractId);


    @Select("SELECT *,category as moneyType" +
            " FROM T_LEDGER" +
            " WHERE id" +
            " IN (SELECT ledgerId FROM T_CONTRACT_LEDGER" +
            " WHERE contractId = #{contractId}) order by modifyTime asc")
    List<LedgerVO> getLedgerList(String contractId);
}
