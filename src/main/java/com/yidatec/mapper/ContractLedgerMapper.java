package com.yidatec.mapper;

import com.yidatec.model.Case;
import com.yidatec.model.ContractLedger;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jrw on 2017/8/3.
 */
@Mapper
public interface ContractLedgerMapper {

    @Insert("INSERT INTO T_CONTRACT_LEDGER (contractId,ledgerId) VALUES (#{contractId},#{ledgerId})")
    int createContarctLedger(ContractLedger contractLedger);

    @Delete("DELETE FROM T_CONTRACT_LEDGER WHERE contractId=#{contractId}")
    int deleteContarctLedger(String contractId);

}
