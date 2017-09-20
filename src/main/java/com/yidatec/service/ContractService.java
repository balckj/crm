package com.yidatec.service;

import com.yidatec.mapper.*;
import com.yidatec.model.*;
import com.yidatec.util.Constants;
import com.yidatec.vo.ABVO;
import com.yidatec.vo.ContractVO;
import com.yidatec.vo.LedgerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 合同
 *
 * @author jrw
 *
 */
@Service("contractService")
public class ContractService {

	@Autowired
	ContractMapper contractMapper;

	@Autowired
	ContractLedgerMapper contractLedgerMapper;

	@Autowired
	LedgerMapper ledgerMapper;


	public List<ABVO> getABList(ProjectEntity projectEntity){
		return contractMapper.getABList(projectEntity);
	}

	public Contract selectContract(String id){
		return  contractMapper.selectContract(id);
	}

	/**
	 * 查询ContractTable
	 *
	 * @return
	 */
	public List<ContractVO> selectContractList(ContractVO contractVO) {
		return  contractMapper.selectContractList(contractVO);
	}

	public int countContractList(ContractVO userVO) {
		return  contractMapper.countContractList(userVO);
	}

	public List<ABVO> selectABVOTableList(ABVO abvo){
		return contractMapper.selectABVOTable(abvo);
	}
	public int countABVOTable(ABVO abvo){
		return contractMapper.countABVOTable(abvo);
	}


	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void deleteContract(String id) {
		contractMapper.delete(id);
	}


	/**
	 * 创建合同
	 *
	 * @param contract
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createContract(Contract contract) {
		contractMapper.create(contract);
	}



	/**
	 * 更新合同
	 *
	 * @param contract
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateContract(Contract contract) {
		contractMapper.update(contract);
	}

	/**
	 * 创建合同历史
	 *
	 * @param contract
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createContractHistory(Contract contract) {
		Contract contract1 = contractMapper.findContract(contract.getId());
		contractMapper.createHistory(contract1);
	}

	/**
	 * 创建台账
	 *
	 * @param
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createLedger(LedgerVO ledgerVO) {
		ledgerMapper.deleteLedger(ledgerVO.getId());// 删除台账表
		contractLedgerMapper.deleteContarctLedger(ledgerVO.getId());// 删除关系表
		ledgerC(ledgerVO);
	}

	/**
	 * 台账create
	 * @param ledgerVO
	 */
	private void ledgerC(LedgerVO ledgerVO) {
		if (ledgerVO.getLedgerList() != null){
			List<Ledger> ledgerList = ledgerVO.getLedgerList();
			LocalDateTime t = LocalDateTime.now();

			for (int i = 0 ; i < ledgerList.size(); i ++) {
				Ledger ledger = ledgerList.get(i);
				String ledgerId = UUID.randomUUID().toString().toLowerCase();
				ContractLedger contractLedger = new ContractLedger();
				contractLedger.setContractId(ledgerVO.getId());
				contractLedger.setLedgerId(ledgerId);
				contractLedgerMapper.createContarctLedger(contractLedger);// 插入关系表

				ledger.setId(ledgerId);
				ledger.setCreatorId(ledgerVO.getId());

				ledger.setModifierId(ledgerVO.getId());
				int numb = i + 1;
				if("true".equals(ledgerVO.getUpdateFlg())){
					// 新加一行
					if(ledger.getModifyTime() == null){
						ledger.setCreateTime(t.plusSeconds(numb));
						ledger.setModifyTime(t.plusSeconds(numb));
						if(i == ledgerList.size()-1){
							ledger.setModifyTime(ledger.getModifyTime().plusSeconds(1));
						}
					}else{
						ledger.setCreateTime(ledger.getCreateTime());
						ledger.setModifyTime(ledger.getModifyTime());
					}

				}else {
					ledger.setCreateTime(t.plusSeconds(numb));
					ledger.setModifyTime(t.plusSeconds(numb));
				}

				ledgerMapper.createLedger(ledger);// 插入台账
			}
		}
	}
}
