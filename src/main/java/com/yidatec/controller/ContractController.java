package com.yidatec.controller;

import com.yidatec.mapper.ContractMapper;
import com.yidatec.mapper.LedgerMapper;
import com.yidatec.model.*;
import com.yidatec.service.*;
import com.yidatec.util.Constants;
import com.yidatec.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by jrw on 2017/7/13.
 */
@Controller
public class ContractController extends BaseController{

    @Autowired
    ProjectService projectService;

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    SequenceService sequenceService;

    @Autowired
    ContractService contractService;

    @Autowired
    ContractMapper contractMapper;
    @Autowired
    LedgerMapper ledgerMapper;

    @RequestMapping("/contractList")
    public String contractList(ModelMap model){
        model.put("moneyTypeList",dictionaryService.selectDictionaryListByCodeCommon(Constants.MONEY_TYPE)); // 款项类型
        model.put("paymentMethodList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PAYMENT_METHOD)); // 支付方式
        model.put("costCenterList",dictionaryService.selectDictionaryListByCodeCommon(Constants.COST_CENTER)); // 成本中心
        model.put("reasonForChangeList",dictionaryService.selectDictionaryListByCodeCommon(Constants.REASON_FORCHANGE)); // 变更原因
        return "contractList";
    }

    @RequestMapping("/contractEdit")
    public String contractEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建合同":"编辑合同");
        model.put("contract",contractMapper.selectContract(id));
        model.put("ledgerList",ledgerMapper.getLedgerList(id));
        model.put("ledgerListFlg",ledgerMapper.getLedgerList(id) != null && ledgerMapper.getLedgerList(id).size() > 0 ? true :false);
        return "contractEdit";
    }

    @RequestMapping("/saveContract")
    @ResponseBody
    public Object saveContract(@Validated @RequestBody Contract contract,
                               BindingResult result)throws Exception{

        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }

        if(contract.getId() == null || contract.getId().trim().length() <= 0) {//新建
            contract.setId(UUID.randomUUID().toString().toLowerCase());
            contract.setCreatorId(getWebUser().getId());
            contract.setCreateTime(LocalDateTime.now());
            contract.setModifierId(getWebUser().getId());
            contract.setModifyTime(contract.getCreateTime());
            contractService.createContract(contract);
        } else {
            contract.setModifierId(getWebUser().getId());
            contract.setModifyTime(LocalDateTime.now());
            contractService.updateContract(contract);
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findContract")
    @ResponseBody
    public Object findContract(@RequestBody ContractVO contractVO)throws Exception{
        List<ContractVO> saleEntityList = contractService.selectContractList(contractVO);
        int count = contractService.countContractList(contractVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", contractVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", saleEntityList);
        return map;
    }


    /**
     * 保存台账
     * @param ledger
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveLedger")
    @ResponseBody
    public Object checkLedgerInfo(@Validated @RequestBody LedgerVO ledger,
                                  BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }

        //新建 更新。此id实际上是合同的id，台账依托于合同，所以这里一定个更新没有新建
        if(ledger.getId() != null || ledger.getId().trim().length() >= 0) {
            contractService.createLedger(ledger);
        }
        return getSuccessJson(null);
    }

    /**
     * 台账必须验证（编辑的时候变更原因必须）
     * @param ledger
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping("/checkLedgerInfo2")
    @ResponseBody
    public Object checkLedgerInfo2(@Validated @RequestBody LedgerVO2 ledger,
                                   BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }

        return getSuccessJson(null);
    }

    /**
     * 查看合同
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/contractView")
    public String contractView(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建合同":"查看合同");
        model.put("contract",contractMapper.selectContract(id));
        return "contractView";
    }

    /**
     * 查看台账
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/ledgerView")
    public String ledgerView(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("contract",contractMapper.selectContract(id));
        model.put("moneyTypeList",dictionaryService.selectDictionaryListByCodeCommon(Constants.MONEY_TYPE)); // 款项类型
        model.put("paymentMethodList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PAYMENT_METHOD)); // 支付方式
        model.put("costCenterList",dictionaryService.selectDictionaryListByCodeCommon(Constants.COST_CENTER)); // 成本中心
        model.put("reasonForChangeList",dictionaryService.selectDictionaryListByCodeCommon(Constants.REASON_FORCHANGE)); // 变更原因
        model.put("ledgerList",ledgerMapper.getLedgerList(id));
        model.put("ledgerListFlg",ledgerMapper.getLedgerList(id) != null && ledgerMapper.getLedgerList(id).size() > 0 ? true :false);
        return "contractLedgerListView";// 返回台账html只读模式
    }

    /**
     * 编辑台账
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/ledgerEdit")
    public String ledgerEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("contract",contractMapper.selectContract(id));
        model.put("moneyTypeList",dictionaryService.selectDictionaryListByCodeCommon(Constants.MONEY_TYPE)); // 款项类型
        model.put("paymentMethodList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PAYMENT_METHOD)); // 支付方式
        model.put("costCenterList",dictionaryService.selectDictionaryListByCodeCommon(Constants.COST_CENTER)); // 成本中心
        model.put("reasonForChangeList",dictionaryService.selectDictionaryListByCodeCommon(Constants.REASON_FORCHANGE)); // 变更原因
        model.put("ledgerList",ledgerMapper.getLedgerList(id));
        model.put("ledgerListSize",
                ledgerMapper.getLedgerList(id) != null && ledgerMapper.getLedgerList(id).size() > 0
                        ? ledgerMapper.getLedgerList(id).size(): 0);
        model.put("ledgerListFlg",ledgerMapper.getLedgerList(id) != null && ledgerMapper.getLedgerList(id).size() > 0 ? true :false);
        return "contractLedgerList";// 返回台账html
    }

    /**
     * 获取合同编号
     * @param contractCodeVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/getContractCode")
    @ResponseBody
    public Object getContractCode(@RequestBody ContractCodeVO contractCodeVO)throws Exception{
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("code",sequenceService.generateContractSequence(contractCodeVO.getType(),contractCodeVO.getCode()));
        return map;
    }

    /**
     * 甲乙合同列表
     * @param model
     * @param projectId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getABList", method= RequestMethod.POST)
    @ResponseBody
    public Object getABList(ModelMap model,@RequestParam(value="projectId",required = false) String projectId)throws Exception{
        HashMap<String,Object> map = new HashMap<String,Object>();
        if(!StringUtils.isEmpty(projectId)){
            ProjectEntity projectEntity = projectService.selectProject(projectId);
            if (projectEntity != null){
                List<ABVO> abvoList = contractService.getABList(projectEntity);
                abvoList.add(setDingS());
                map.put("ABList",abvoList);
            }else{
                map.put("ABList","0");
            }
        }else {
            map.put("ABList","0");
        }

        return map;
    }



    /**
     * 获取甲乙合同的查询条件表
     * @param abvo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/loadABTable")
    @ResponseBody
    public Object loadABTable(@RequestBody ABVO abvo)throws Exception{
        List<ABVO> abvoList = contractService.selectABVOTableList(abvo);
        abvoList.add(setDingS());
        int count = contractService.countABVOTable(abvo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", abvo.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", abvoList);
        return map;
    }

    /**
     * 固定鼎世
     * @return
     */
    private ABVO setDingS(){
        ABVO abvo1 = new ABVO();
        abvo1.setId(Constants.DING_SHI_ID);
        abvo1.setName(Constants.DING_SHI_NAME);
        return abvo1;
    }
}
