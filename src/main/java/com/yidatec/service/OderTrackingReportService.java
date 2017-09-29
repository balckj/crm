package com.yidatec.service;

import com.yidatec.mapper.ReportMapper;
import com.yidatec.mapper.UserMapper;
import com.yidatec.model.Dictionary;
import com.yidatec.model.User;
import com.yidatec.util.Constants;
import com.yidatec.vo.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业绩管理报表
 *
 * @author jrw
 *
 */
@Service("oderTrackingReportService")
public class OderTrackingReportService {

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DictionaryService dictionaryService;

    static String dianxiangCostId = "edd579ae-06b3-470a-bbb1-df8c5b483821";//电箱费id
    static String manageCostId = "5040440e-cc00-405a-8b4b-9428667dc034";//管理费id
    static String shentuCostId = "e03ca4b7-ced0-46ca-a55a-576e2ea882d6";//审图费id
    static String baoxianCostId = "a96bcc9e-eb1b-4c24-95aa-942aed41bba9";//保险费id
    static String saleCostId = "23d62f69-709a-4f42-b137-cdbc6eec7683";//营销费id
    static List<String> costKeyList =new ArrayList<String>(5);
    static{
        costKeyList.add(dianxiangCostId);
        costKeyList.add(manageCostId);
        costKeyList.add(shentuCostId);
        costKeyList.add(baoxianCostId);
        costKeyList.add(saleCostId);
    }

    public void generateOderTrackingReport(XSSFWorkbook wb,
                                          String starTime,String endTime) throws Exception {



        List<PerformanceReportVO> performanceReportVOList =  reportMapper.selectPerformanceReportVOBaseList(starTime,endTime);
        int size = performanceReportVOList == null?0:(performanceReportVOList.size() *4/3)+1;
        Map<String,Map<String,List<PerformanceReportVO>>> map = new HashMap<String,Map<String,List<PerformanceReportVO>>>(size);
        List<String> projectIdList = new ArrayList<String>(performanceReportVOList == null?0:performanceReportVOList.size());//记录顺序
        Map<String,PerformanceReportVO> projectIdToProjectMap = new HashMap<String,PerformanceReportVO>(size);

        if(performanceReportVOList != null) {
            for (PerformanceReportVO one : performanceReportVOList) {

                Map<String, List<PerformanceReportVO>> categoryAndSupplierToContractMap = map.get(one.getProjectId());

                if (categoryAndSupplierToContractMap == null) {
                    categoryAndSupplierToContractMap = new HashMap<String, List<PerformanceReportVO>>(8);
                    map.put(one.getProjectId(), categoryAndSupplierToContractMap);
                    projectIdList.add(one.getProjectId());
                    projectIdToProjectMap.put(one.getProjectId(),one);
                }

                //合同分类
                if(one.getContractCategory() != null ) {//有合同
                    List<PerformanceReportVO> oneCategoryList = categoryAndSupplierToContractMap.get(one.getContractCategory());
                    if (oneCategoryList == null) {
                        oneCategoryList = new ArrayList<PerformanceReportVO>(10);
                        categoryAndSupplierToContractMap.put(one.getContractCategory(), oneCategoryList);
                    }
                    if (one.getContractId() != null) {
                        oneCategoryList.add(one);
                    }
                }

                //供应商到合同映射，兼容同一项母同一供应商有多个合同的情况
                //项目经理
//				List<PerformanceReportVO> onePmList = categoryAndSupplierToContractMap.get(one.getPmId());
//				if (onePmList == null) {
//					onePmList = new ArrayList<PerformanceReportVO>(10);
//					categoryAndSupplierToContractMap.put(one.getPmId(), onePmList);
//				}
//				onePmList.add(one);

                //开发销售
//				List<PerformanceReportVO> oneDevSaleList = categoryAndSupplierToContractMap.get(one.getDevelopSaleId());
//				if (oneDevSaleList == null) {
//					oneDevSaleList = new ArrayList<PerformanceReportVO>(10);
//					categoryAndSupplierToContractMap.put(one.getDevelopSaleId(), oneDevSaleList);
//				}
//				oneDevSaleList.add(one);

                //跟进销售
//				List<PerformanceReportVO> oneTraceSaleList = categoryAndSupplierToContractMap.get(one.getTraceSaleId());
//				if (oneTraceSaleList == null) {
//					oneTraceSaleList = new ArrayList<PerformanceReportVO>(10);
//					categoryAndSupplierToContractMap.put(one.getTraceSaleId(), oneTraceSaleList);
//				}
//				oneTraceSaleList.add(one);

                //乙方到合同映射，兼容一个乙方多个合同
                if(one.getContractSecondParty() != null) {//有合同
                    List<PerformanceReportVO> oneSecondList = categoryAndSupplierToContractMap.get(one.getContractSecondParty());
                    if (oneSecondList == null) {
                        oneSecondList = new ArrayList<PerformanceReportVO>(10);
                        categoryAndSupplierToContractMap.put(one.getContractSecondParty(), oneSecondList);
                    }
                    oneSecondList.add(one);
                }


            }
        }

        //项目到设计师的映射
        List<DesignerReportVO> designerList = reportMapper.selectProjectDesigner(starTime,endTime);
        Map<String,List<DesignerReportVO>> projectToDesignerMap = new HashMap<String,List<DesignerReportVO>>(designerList==null?0:(designerList.size()*4/3)+1);

        if(designerList != null){
            for(DesignerReportVO one : designerList){
                List<DesignerReportVO> oneProjectDesignerLst = projectToDesignerMap.get(one.getProjectId());
                if(oneProjectDesignerLst == null){
                    oneProjectDesignerLst = new ArrayList<DesignerReportVO>();
                    projectToDesignerMap.put(one.getProjectId(),oneProjectDesignerLst);
                }
                oneProjectDesignerLst.add(one);
            }
        }

        //项目到工厂的映射
        List<FactoryReportVO> factoryList = reportMapper.selectProjectFactory(starTime, endTime);
        Map<String,List<FactoryReportVO>> projectToFactoryMap = new HashMap<String,List<FactoryReportVO>>(factoryList==null?0:(factoryList.size()*4/3)+1);
        if(factoryList != null){
            for(FactoryReportVO one : factoryList){
                List<FactoryReportVO> oneProjectFactoryLst = projectToFactoryMap.get(one.getProjectId());
                if(oneProjectFactoryLst == null){
                    oneProjectFactoryLst = new ArrayList<FactoryReportVO>();
                    projectToFactoryMap.put(one.getProjectId(),oneProjectFactoryLst);
                }
                oneProjectFactoryLst.add(one);
            }
        }

        //台账数据处理，合同到台账的映射
        List<LedgerReportVO> ledgerReportVOList = reportMapper.selectLedgerReportList();
        Map<String,List<LedgerReportVO>> contractToLedgerMap = new HashMap<String,List<LedgerReportVO>>(ledgerReportVOList == null?0:(ledgerReportVOList.size()*4/3)+1);
        if(ledgerReportVOList != null){
            for(LedgerReportVO one : ledgerReportVOList){
                List<LedgerReportVO> oneContractLedgerList = contractToLedgerMap.get(one.getContractId());
                if(oneContractLedgerList == null){
                    oneContractLedgerList = new ArrayList<LedgerReportVO>();
                    contractToLedgerMap.put(one.getContractId(),oneContractLedgerList);
                }
                oneContractLedgerList.add(one);
            }
        }

        List<UserVO> userList = userMapper.findAllUser();
        Map<String,UserVO> userMap = new HashMap<String,UserVO>((userList.size() * 4 / 3) +1);
        for(UserVO one : userList){
            userMap.put(one.getId(),one);
        }



        XSSFSheet sheet = wb.createSheet("sheet1");
        Map<String, XSSFCellStyle> mapStyle = createStyles(wb);
        sheet.setDisplayGridlines(false);

        // 起始行号
        XSSFRow row0 = null;
        // 设定标题行号，生成标题
        int rowNum = 0;
        row0 = sheet.createRow(rowNum++);



        XSSFRow row1 = sheet.createRow(rowNum++);

        int colIndex = 0;

        row1.createCell(colIndex).setCellValue("合同编号");
        sheet.setColumnWidth(colIndex++, 32 * 160);

        row1.createCell(colIndex).setCellValue("项目名称");
        sheet.setColumnWidth(colIndex++, 32 * 120);

        row1.createCell(colIndex).setCellValue("市场活动名称");
        sheet.setColumnWidth(colIndex++, 32 * 100);

        row1.createCell(colIndex).setCellValue("活动类型");
        sheet.setColumnWidth(colIndex++, 32 * 80);

        row1.createCell(colIndex).setCellValue("开展日期");
        sheet.setColumnWidth(colIndex++, 32 * 260);

        row1.createCell(colIndex).setCellValue("展位号");
        sheet.setColumnWidth(colIndex++, 32 * 50);

        row1.createCell(colIndex).setCellValue("合同价");
        sheet.setColumnWidth(colIndex++, 32 * 80);

        row1.createCell(colIndex).setCellValue("合同变更");
        sheet.setColumnWidth(colIndex++, 32 * 150);

        row1.createCell(colIndex).setCellValue("客户扣款");
        sheet.setColumnWidth(colIndex++, 32 * 80);

        row1.createCell(colIndex).setCellValue("扣款理由");
        sheet.setColumnWidth(colIndex++, 32 * 120);

        row1.createCell(colIndex).setCellValue("执行地");
        sheet.setColumnWidth(colIndex++, 32 * 80);


        row1.createCell(colIndex).setCellValue("成本中心");
        sheet.setColumnWidth(colIndex++, 32 * 80);


        row1.createCell(colIndex).setCellValue("实际业绩");
        sheet.setColumnWidth(colIndex++, 32 * 80);


        row1.createCell(colIndex).setCellValue("毛利润");
        sheet.setColumnWidth(colIndex++, 32 * 80);


        row1.createCell(colIndex).setCellValue("百分比");
        sheet.setColumnWidth(colIndex++, 32 * 80);


        row1.createCell(colIndex).setCellValue("供应商");
        sheet.setColumnWidth(colIndex++, 32 * 160);


        row1.createCell(colIndex).setCellValue("供应商合同额及变动");
        sheet.setColumnWidth(colIndex++, 32 * 120);


        row1.createCell(colIndex).setCellValue("供应商扣款");
        sheet.setColumnWidth(colIndex++, 32 * 120);


        row1.createCell(colIndex).setCellValue("最终供应商价格");
        sheet.setColumnWidth(colIndex++, 32 * 120);


        row1.createCell(colIndex).setCellValue("供应商合同编号");
        sheet.setColumnWidth(colIndex++, 32 * 160);



        List<Dictionary> ledgerItemDefineList = dictionaryService.selectDictionaryListByCodeCommon(Constants.MONEY_TYPE);

        List<Dictionary> costItemDefineList = new ArrayList<Dictionary>(5);


        if(ledgerItemDefineList != null){
            for(int i = 0;i < ledgerItemDefineList.size();i++){
                Dictionary one = ledgerItemDefineList.get(i);
                String id = one.getId();
                if(id.equalsIgnoreCase(dianxiangCostId)||id.equalsIgnoreCase(manageCostId)||id.equalsIgnoreCase(shentuCostId)||id.equalsIgnoreCase(baoxianCostId)||id.equalsIgnoreCase(saleCostId)){
                    costItemDefineList.add(one);
//					costKeyList.add(id);
                }
            }
        }
        ledgerItemDefineList = costItemDefineList;

        if(ledgerItemDefineList != null){
            for(int i = 0;i < ledgerItemDefineList.size();i++){
                row1.createCell(colIndex).setCellValue(ledgerItemDefineList.get(i).getValue());
                sheet.setColumnWidth(colIndex++, 32 * 50);
            }
        }
        row1.createCell(colIndex).setCellValue("签单部门");
        sheet.setColumnWidth(colIndex++, 32 * 100);


        row1.createCell(colIndex).setCellValue("业务员");
        sheet.setColumnWidth(colIndex++, 32 * 100);


        List<Dictionary> designerItemDefineList = dictionaryService.selectDictionaryListByCodeCommon(Constants.DESIGNER_CATEGORY);
        if(designerItemDefineList != null){
            for(int i = 0;i < designerItemDefineList.size();i++){
                row1.createCell(colIndex).setCellValue(designerItemDefineList.get(i).getValue());
                sheet.setColumnWidth(colIndex++, 32 * 120);
            }
        }
        row1.createCell(colIndex).setCellValue("客户来源");
        sheet.setColumnWidth(colIndex++, 32 * 80);


        row1.createCell(colIndex).setCellValue("客户的创建者");
        sheet.setColumnWidth(colIndex++, 32 * 100);


        row1.createCell(colIndex).setCellValue("面积/㎡");
        sheet.setColumnWidth(colIndex++, 32 * 80);



        row1.setHeightInPoints(30);

        for(int i = 0 ; i < colIndex ; i++){
            row1.getCell(i).setCellStyle(mapStyle.get("header_6"));
        }



        for(String projectId : projectIdList){
            Map<String, List<PerformanceReportVO>> casMap = map.get(projectId);//当项目没有合同时，casMap.size()为0
            List<PerformanceReportVO> saleContractList = casMap.get("C");
            int saleContractCount = saleContractList == null ? 0:saleContractList.size();
            int supplierContractCount = 0;//一个项目所有供应商合同数量
            BigDecimal supplierLedgerAmount = new BigDecimal(0);//一个项目所有供应商的台账总额
            BigDecimal costLedgerAmount = new BigDecimal(0);//一个项目所有供应商的成本总额（那五项）
            for(String key : casMap.keySet()){
                if(key.length()>1){
                    supplierContractCount++;
                }
            }


            List<Object> supplierList = new ArrayList<Object>();
            List<PerformanceReportVO> contractList = new ArrayList<PerformanceReportVO>();

            int needTotalRowNumber = 0;

            PerformanceReportVO sample = projectIdToProjectMap.get(projectId);
            if(sample.getPmId() != null && !sample.getPmId().trim().isEmpty()){
                supplierList.add(sample.getPmId());
                List<PerformanceReportVO> sl = casMap.get(sample.getPmId());
                if(sl != null) {
                    contractList.addAll(sl);
                    needTotalRowNumber += sl.size();
                }else {
                    needTotalRowNumber++;
                }
            }
            if(sample.getDevelopSaleId() != null && !sample.getDevelopSaleId().trim().isEmpty()){
                if(!supplierList.contains(sample.getDevelopSaleId())) {
                    supplierList.add(sample.getDevelopSaleId());
                    List<PerformanceReportVO> sl = casMap.get(sample.getDevelopSaleId());
                    if(sl != null) {
                        contractList.addAll(sl);
                        needTotalRowNumber += sl.size();
                    }else {
                        needTotalRowNumber++;
                    }
                }
            }
            if(sample.getTraceSaleId() != null && !sample.getTraceSaleId().trim().isEmpty()){
                if(!supplierList.contains(sample.getTraceSaleId())) {
                    supplierList.add(sample.getTraceSaleId());
                    List<PerformanceReportVO> sl = casMap.get(sample.getTraceSaleId());
                    if(sl != null) {
                        contractList.addAll(sl);
                        needTotalRowNumber += sl.size();
                    }else {
                        needTotalRowNumber++;
                    }
                }
            }
            List<DesignerReportVO> oneProjectDesignerList = projectToDesignerMap.get(projectId);
            Map<String,DesignerReportVO> designerReportVOMap = new HashMap<String,DesignerReportVO>(oneProjectDesignerList.size());
            if(oneProjectDesignerList != null){
                for(DesignerReportVO dr : oneProjectDesignerList) {
                    if (!supplierList.contains(dr.getId())) {
                        supplierList.add(dr);
                        List<PerformanceReportVO> sl = casMap.get(dr.getId());
                        if(sl != null) {
                            contractList.addAll(sl);
                            needTotalRowNumber += sl.size();
                        }else {
                            needTotalRowNumber++;
                        }
                    }
                    designerReportVOMap.put(dr.getDesignerCategory(),dr);
                }
            }

            List<FactoryReportVO> oneProjectFactoryList = projectToFactoryMap.get(projectId);
            if(oneProjectFactoryList != null){
                for(FactoryReportVO fr : oneProjectFactoryList) {
                    if (!supplierList.contains(fr.getId())) {
                        supplierList.add(fr);
                        List<PerformanceReportVO> sl = casMap.get(fr.getId());
                        if(sl != null) {
                            contractList.addAll(sl);
                            needTotalRowNumber += sl.size();
                        }else {
                            needTotalRowNumber++;
                        }
                    }
                }
            }
            int supplierCount = supplierList.size();


            int k = 0;// 一个项目的准确的行号指示器
            //循环处理一个项目的每一行
            for(int i = 0 ; i < (saleContractCount > 1? needTotalRowNumber * saleContractCount : needTotalRowNumber) ; i++){
                colIndex = 0;
                XSSFRow row = null;
                row = sheet.getRow(rowNum);
                if(row == null) {
                    row = sheet.createRow(rowNum);
                }
                //销售合同编号打印
                PerformanceReportVO one = null;
                if(saleContractCount > 0) {//有销售合同的
                    if ( i%needTotalRowNumber == 0) {//打印销售合同
                        supplierLedgerAmount = new BigDecimal(0);//一个项目所有供应商的台账总额
                        one = saleContractList.get(i/needTotalRowNumber);
                        row.createCell(colIndex++).setCellValue(one.getContractCode());

                        row.createCell(colIndex++).setCellValue(one.getProjectName());
                        row.createCell(colIndex++).setCellValue(one.getCampaignName());
                        row.createCell(colIndex++).setCellValue(one.getCampaignType() == null ? "" : dictionaryService.selectDictionary(one.getCampaignType()).getValue());
                        row.createCell(colIndex++).setCellValue(one.getCampaignStartEndTime());
                        row.createCell(colIndex++).setCellValue(one.getExhibitionNumber());
                        row.createCell(colIndex++).setCellValue(one.getContractCountAmount());
                        row.createCell(colIndex++).setCellValue(one.getContractCountAmountChange());

                        Map<String,Object> ledgerMap = doledgerMap(contractToLedgerMap, one.getContractId());
                        Object ledgerAmount = ledgerMap.get("total");
                        String contractCountAmount = one.getContractCountAmount();
                        if(ledgerAmount == null){
                            row.createCell(colIndex++).setCellValue("");//若台账总额为空（没有台账），打印"-",表示不存在差额
                        }else {
                            if (contractCountAmount != null && !contractCountAmount.trim().isEmpty()) {
                                row.createCell(colIndex++).setCellValue(((BigDecimal)ledgerAmount).subtract(new BigDecimal(contractCountAmount)).toString());
                            } else {
                                row.createCell(colIndex++).setCellValue("");//这种情况不应该发生，合同总额是必填项
                            }
                        }
                        String reason = retrieveLastLedgerReasonForChange(contractToLedgerMap, one.getContractId());
                        row.createCell(colIndex++).setCellValue(reason);//这种情况不应该发生，合同总额是必填项
                        row.createCell(colIndex+1).setCellValue(retrieveLastLedgerCostCenter(contractToLedgerMap, one.getContractId()));
                        row.createCell(colIndex++).setCellValue(one.getAddress());

                    } else {//打印剩下行单元格 用于合并
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex+1);
                        row.createCell(colIndex++);
                    }
                }else{//无销售合同的
                    row.createCell(colIndex++);
                    if(k/needTotalRowNumber == 0) {//打印项目信息
                        row.createCell(colIndex++).setCellValue(sample.getProjectName());
                        row.createCell(colIndex++).setCellValue(sample.getCampaignName());
                        row.createCell(colIndex++).setCellValue(sample.getCampaignType() == null ? "" : dictionaryService.selectDictionary(sample.getCampaignType()).getValue());
                        row.createCell(colIndex++).setCellValue(sample.getCampaignStartEndTime());
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex+1);
                        row.createCell(colIndex++).setCellValue(sample.getAddress());
                    }else{//仅打印空单元格 用于合并
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex++);
                        row.createCell(colIndex+1);
                        row.createCell(colIndex++);
                    }
                }


                colIndex++;

                colIndex +=3;

                //输出供应商相关
                XSSFRow oldRow = null;
                Object obj = null;
                if(i%needTotalRowNumber < supplierCount){
                    obj = supplierList.get(i%needTotalRowNumber);
                    String res = "";
                    String secondId = null;
                    if(obj instanceof String){
                        User user = userMap.get(obj);
                        if(user != null){
                            res = user.getName();
                            secondId = user.getId();
                        }
                    }else if(obj instanceof DesignerReportVO){
                        res = ((DesignerReportVO)obj).getName();
                        secondId = ((DesignerReportVO)obj).getId();
                    }else if(obj instanceof FactoryReportVO){
                        res = ((FactoryReportVO)obj).getName();
                        secondId = ((FactoryReportVO)obj).getId();
                    }
                    if(secondId != null){
                        oldRow = row;
                        row = sheet.getRow(rowNum > 0 ? rowNum + k - i : rowNum + k);
                        if(row == null){
                            row = sheet.createRow(rowNum > 0 ? rowNum + k - i : rowNum + k);
                        }
                        List<PerformanceReportVO> oneSupplierContractList = casMap.get(secondId);
                        if(oneSupplierContractList != null && oneSupplierContractList.size() > 0){
                            for(int j = 0 ; j < oneSupplierContractList.size() ; j++){
                                //设置供应商名称
                                if(j == 0){
                                    row.createCell(colIndex).setCellValue(res);
                                }else{
                                    row = sheet.createRow(rowNum+k-i);
                                    row.createCell(colIndex);
                                }
                                setOneCellStyle(row,colIndex,mapStyle);
                                //设置供应商合同总额
                                PerformanceReportVO oneContract = oneSupplierContractList.get(j);
                                row.createCell(colIndex+1).setCellValue(oneContract.getContractCountAmount());
                                setOneCellStyle(row,colIndex+1,mapStyle);

                                if(oneContract != null) {
                                    Map<String,Object> ledgerMap = doledgerMap(contractToLedgerMap, oneContract.getContractId());
                                    Object ledgerAmount = ledgerMap.get("total");
                                    Object costAmount = ledgerMap.get("costTotal");
                                    //设置供应商扣款
                                    String contractCountAmount = oneContract.getContractCountAmount();
                                    if(ledgerAmount == null){
                                        row.createCell(colIndex+2).setCellValue("");//若台账总额为空（没有台账），打印"-",表示不存在差额
                                    }else {
                                        if (contractCountAmount != null && !contractCountAmount.trim().isEmpty()) {
                                            row.createCell(colIndex+2).setCellValue(((BigDecimal)ledgerAmount).subtract(new BigDecimal(contractCountAmount)).toString());
                                        } else {
                                            row.createCell(colIndex+2).setCellValue("");//这种情况不应该发生，合同总额是必填项
                                        }
                                        supplierLedgerAmount = supplierLedgerAmount.add(((BigDecimal)ledgerAmount));

                                    }
                                    if(costAmount != null){
                                        costLedgerAmount = costLedgerAmount.add(((BigDecimal)costAmount));
                                    }
                                    setOneCellStyle(row,colIndex+2,mapStyle);
                                    //设置最终供应商价格
                                    row.createCell(colIndex+3).setCellValue(ledgerAmount == null ? "":ledgerAmount.toString());//这种情况不应该发生，合同总额是必填项
                                    setOneCellStyle(row,colIndex+3,mapStyle);
                                    //设置供应商合同代码
                                    row.createCell(colIndex+4).setCellValue(oneContract.getContractCode());
                                    setOneCellStyle(row,colIndex+4,mapStyle);

                                    for(int n = 0 ; n < ledgerItemDefineList.size() ; n++){
                                        Object o = ledgerMap.get(ledgerItemDefineList.get(n).getId());
                                        if(o != null){
                                            BigDecimal val = (BigDecimal)o;
                                            row.createCell(colIndex+4 + (n+1)).setCellValue(val.toString());
                                            setOneCellStyle(row,colIndex+4+ (n+1),mapStyle);
                                        }else{
                                            row.createCell(colIndex+4 + (n+1));
                                            setOneCellStyle(row,colIndex+4+ (n+1),mapStyle);
                                        }
                                    }

                                    //打印从签单部门到客户创建者列的单元格，但是不打印单元格内容，内容在合并时一起输出
                                    int deptIndex = colIndex + 4 + (ledgerItemDefineList.size() + 1);
                                    int customerIndex = colIndex + 4 + (ledgerItemDefineList.size() + 2) + designerItemDefineList.size() + 2;

                                    for(; deptIndex <= customerIndex; deptIndex++) {
                                        row.createCell(deptIndex);
                                        setOneCellStyle(row,deptIndex,mapStyle);
                                    }

                                    //面积
                                    row.createCell(colIndex+4 + (ledgerItemDefineList.size()+2) + designerItemDefineList.size()+3).setCellValue(oneContract.getContractArea());
                                    setOneCellStyle(row,colIndex+4 + (ledgerItemDefineList.size()+2) + designerItemDefineList.size()+3,mapStyle);
                                }
                                else{//不应该发生这种情况
//									for(int n = 0 ; n < ledgerItemDefineList.size() + designerItemDefineList.size()+8; n++) {
//										row.createCell(colIndex + n);
//										setOneCellStyle(row,colIndex + n,mapStyle);
//									}
                                }
                                k++;
                            }
                            if(oneSupplierContractList.size() > 1) {
                                CellRangeAddress region = new CellRangeAddress(rowNum, rowNum+k-i-1, colIndex, colIndex);
                                sheet.addMergedRegion(region);
                            }
                        }else{
                            row.createCell(colIndex).setCellValue(res);
                            setOneCellStyle(row,colIndex,mapStyle);
                            row.createCell(colIndex+1).setCellValue("");
                            setOneCellStyle(row,colIndex+1,mapStyle);
                            for(int n = 0 ; n < ledgerItemDefineList.size() + designerItemDefineList.size()+8; n++) {
                                row.createCell(colIndex + 2 + n);
                                setOneCellStyle(row,colIndex + 2 + n,mapStyle);
                            }
                            k++;
                        }
                    } else{//不应该发生这种情况

                    }
                }

                if(oldRow!= null)
                    row = oldRow;
                setCellStyle(row,colIndex,mapStyle);
                if(saleContractCount > 0) {//有销售合同的
                    XSSFRow blankRow = sheet.getRow(rowNum);
                    blankRow.createCell(colIndex-3);
                    setOneCellStyle(blankRow,colIndex-3,mapStyle);
                    blankRow.createCell(colIndex-2);
                    setOneCellStyle(blankRow,colIndex-2,mapStyle);
                    blankRow.createCell(colIndex-1);
                    setOneCellStyle(blankRow,colIndex-1,mapStyle);
                    if (i%needTotalRowNumber == needTotalRowNumber - 1) {//打印实际业绩、毛利润、百分比
                        PerformanceReportVO oneSaleContract = saleContractList.get(i/needTotalRowNumber);
                        Map<String,Object> ledgerMap = doledgerMap(contractToLedgerMap, oneSaleContract.getContractId());
                        XSSFRow saleContractRow = sheet.getRow(rowNum - needTotalRowNumber +1);
                        Object total = ledgerMap.get("total");
                        BigDecimal totalObj = total== null ? new BigDecimal(0):(BigDecimal)total;
                        String change = oneSaleContract.getContractCountAmountChange() ;
                        BigDecimal changeObj = (change == null || change.trim().isEmpty())?new BigDecimal(0):new BigDecimal(change);
                        BigDecimal a = (totalObj.add(changeObj).subtract(costLedgerAmount));
                        saleContractRow.createCell(colIndex-3).setCellValue(a.toString());//实际业绩
                        setOneCellStyle(saleContractRow,colIndex-3,mapStyle);
                        BigDecimal b = (totalObj.add(changeObj).subtract(supplierLedgerAmount));//毛利润
                        saleContractRow.createCell(colIndex-2).setCellValue(b.toString());//原始需求减去两次成本台账，我稍作修改减去供应商台账总额
                        setOneCellStyle(saleContractRow,colIndex-2,mapStyle);
                        saleContractRow.createCell(colIndex-1).setCellValue(a.compareTo(BigDecimal.ZERO) ==0 ? null :  b.multiply(new BigDecimal(100)).divide(a,2, RoundingMode.HALF_UP).toString()+"%");//原始需求减去两次成本台账，我稍作修改减去供应商台账总额
                        setOneCellStyle(saleContractRow,colIndex-1,mapStyle);

                        for(int e = 0 ; e < colIndex ; e++) {
                            CellRangeAddress region = new CellRangeAddress(rowNum - needTotalRowNumber+1, rowNum, e, e);
                            sheet.addMergedRegion(region);
                        }

                    }
                }else{//无销售合同的
                    if (i%needTotalRowNumber == needTotalRowNumber - 1) {
                        for (int p = 0; p < needTotalRowNumber; p++) {
                            XSSFRow blankRow = sheet.getRow(rowNum - needTotalRowNumber + p +1);
                            blankRow.createCell(colIndex - 3);
                            setOneCellStyle(blankRow, colIndex - 3, mapStyle);
                            blankRow.createCell(colIndex - 2);
                            setOneCellStyle(blankRow, colIndex - 2, mapStyle);
                            blankRow.createCell(colIndex - 1);
                            setOneCellStyle(blankRow, colIndex - 1, mapStyle);
                        }

                        //合并供应商列以前的行
                        if(needTotalRowNumber>1){
                            for(int e = 0 ; e < colIndex ; e++) {
                                CellRangeAddress region = new CellRangeAddress(rowNum - needTotalRowNumber + 1, rowNum, e, e);
                                sheet.addMergedRegion(region);
                            }
                        }
                    }
                }

                if (i%needTotalRowNumber == needTotalRowNumber - 1) {
                    XSSFRow oneProjectFirstRow = sheet.getRow(rowNum - (needTotalRowNumber - 1));
                    //签单部门
                    UserVO user = userMap.get(sample.getProjectCreatorId());
                    XSSFCell cell = oneProjectFirstRow.getCell(colIndex + 4 + (ledgerItemDefineList.size() + 1));
                    if (user != null) {
                        cell.setCellValue(user.getRoleNames());
                    }

                    //业务员
                    cell = oneProjectFirstRow.getCell(colIndex + 4 + (ledgerItemDefineList.size() + 2));
                    if (user != null) {
                        cell.setCellValue(user.getName());
                    }

                    //设计师
                    for (int n = 0; n < designerItemDefineList.size(); n++) {
                        DesignerReportVO o = designerReportVOMap.get(designerItemDefineList.get(n).getId());
                        if (o != null) {
                            oneProjectFirstRow.getCell(colIndex + 4 + (ledgerItemDefineList.size() + 2) + (n + 1)).setCellValue(o.getName());
                        }
                    }

                    //客户来源
                    Dictionary source = dictionaryService.selectDictionary(sample.getCustomerSource());
                    String v = source == null ? null : source.getValue();
                    oneProjectFirstRow.getCell(colIndex + 4 + (ledgerItemDefineList.size() + 2) + designerItemDefineList.size() + 1).setCellValue(v);

                    //客户创建者
                    User customerCreator = userMap.get(sample.getCustomerCreatorId());
                    String u = customerCreator == null ? null : customerCreator.getName();
                    oneProjectFirstRow.getCell(colIndex + 4 + (ledgerItemDefineList.size() + 2) + designerItemDefineList.size() + 2).setCellValue(u);

                    int deptIndex = colIndex + 4 + (ledgerItemDefineList.size() + 1);
                    int customerIndex = colIndex + 4 + (ledgerItemDefineList.size() + 2) + designerItemDefineList.size() + 2;
                    for (; deptIndex <= customerIndex; deptIndex++) {
                        CellRangeAddress region = new CellRangeAddress(rowNum - needTotalRowNumber + 1, rowNum, deptIndex, deptIndex);
                        sheet.addMergedRegion(region);

                    }
                }
                rowNum++;
            }
        }
    }

    private void setCellStyle(XSSFRow row,int colIndex,Map<String, XSSFCellStyle> mapStyle){
        for(int m = 0 ; m < colIndex ; m++){
            XSSFCell cell = row.getCell(m);
            if(cell != null)
                cell.setCellStyle(mapStyle.get("data_4"));
        }
    }

    private void setOneCellStyle(XSSFRow row,int colIndex,Map<String, XSSFCellStyle> mapStyle){
        XSSFCell cell = row.getCell(colIndex);
        if(cell != null)
            cell.setCellStyle(mapStyle.get("data_4"));
    }

    private Map<String,Object> doledgerMap(Map<String,List<LedgerReportVO>> contractToLedgerMap, String contractId){
        List<LedgerReportVO> ledgerList = contractToLedgerMap.get(contractId);
        Map<String,Object> map = new HashMap<String,Object>(15);
        if(ledgerList != null){
            BigDecimal total = new BigDecimal(0);
            BigDecimal costTotal = new BigDecimal(0);
            for(LedgerReportVO one : ledgerList){
                BigDecimal amo = one.getPaymentAmount();
                total = total.add(amo);
                Object tmp = map.get(one.getMoneyType());
                if(tmp == null) {
                    map.put(one.getMoneyType(), amo);
                }else{
                    map.put(one.getMoneyType(),((BigDecimal)tmp).add(amo));
                }
                if(costKeyList.contains(one.getMoneyType())){
                    costTotal.add(amo);
                }
            }
            map.put("total",total);
            map.put("costTotal",costTotal);
        }
        return map;
    }


    private String retrieveLastLedgerReasonForChange(Map<String,List<LedgerReportVO>> contractToLedgerMap, String contractId){
        List<LedgerReportVO> ledgerList = contractToLedgerMap.get(contractId);

        if(ledgerList != null){
            String reason = ledgerList.get(0).getReasonForChange();
            if(reason != null && !reason.trim().isEmpty()){
                return dictionaryService.selectDictionary(reason).getValue();
            }
            return "";
        }
        return "";
    }

    private String retrieveLastLedgerCostCenter(Map<String,List<LedgerReportVO>> contractToLedgerMap, String contractId){
        List<LedgerReportVO> ledgerList = contractToLedgerMap.get(contractId);

        if(ledgerList != null){
            return dictionaryService.selectDictionary(ledgerList.get(0).getCostCenter()).getValue();
        }
        return "";
    }


    private XSSFCellStyle createBorderedStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        return style;
    }

    private Map<String, XSSFCellStyle> createStyles(XSSFWorkbook wb) {
        Map<String, XSSFCellStyle> styles = new HashMap<String, XSSFCellStyle>();
        //白色背景标题 15号粗体黑字 居左
        XSSFCellStyle style;
        Font headerFont1 = wb.createFont();
        headerFont1.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont1.setFontHeightInPoints((short) 18);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(headerFont1);
        styles.put("header_1", style);
        //白色背景标题 10号粗体黑字
        Font headerFont2 = wb.createFont();
        headerFont2.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont2.setFontHeightInPoints((short) 10);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(headerFont2);
        styles.put("header_2", style);
        //绿色背景标题 10号粗体黑字
        style = createBorderedStyle(wb);
        headerFont2.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont2.setFontHeightInPoints((short) 10);
        style.setFillForegroundColor(IndexedColors.LIME.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(headerFont2);
        styles.put("header_3", style);

        //白色背景标题 10号粗体红字
        Font headerFont3 = wb.createFont();
        headerFont3.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont3.setColor(IndexedColors.RED.getIndex());
        headerFont3.setFontHeightInPoints((short) 10);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(headerFont3);
        styles.put("header_4", style);

        //白色背景标题 15号粗体黑字 居中
        style = createBorderedStyle(wb);
        headerFont3.setFontHeightInPoints((short) 10);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(headerFont1);
        styles.put("header_5", style);

        //白色背景标题 9号粗体黑字
        Font headerFont4 = wb.createFont();
        headerFont4.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont4.setFontHeightInPoints((short) 10);
        headerFont4.setColor(IndexedColors.WHITE.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(headerFont4);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        styles.put("header_6", style);

        //白色背景标题 9号粗体黑字 版本标题用
        Font headerFont5 = wb.createFont();
        headerFont5.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont5.setFontHeightInPoints((short) 9);
        headerFont5.setColor(IndexedColors.RED.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(headerFont5);
        styles.put("header_ver", style);


        //白色背景标题 9号粗体黑字 剧中黑色背景
        Font headerFont6 = wb.createFont();
        headerFont6.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont6.setFontHeightInPoints((short) 9);
        headerFont6.setColor(IndexedColors.WHITE.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND );
        style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        style.setFont(headerFont6);
        styles.put("header_7", style);

        // 白色背景标题 9号黑字
        Font dataFont1 = wb.createFont();
        dataFont1.setFontHeightInPoints((short) 9);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(dataFont1);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        // 设置单元格内容垂直居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        styles.put("data_1", style);

        // 绿色背景标题 10号黑字
        style = createBorderedStyle(wb);
        style.setFillForegroundColor(IndexedColors.LIME.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(dataFont1);
        styles.put("data_2", style);

        //白色背景标题 10号红字
        Font dataFont2 = wb.createFont();
        dataFont2.setFontHeightInPoints((short) 10);
        dataFont2.setColor(IndexedColors.RED.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(dataFont2);
        styles.put("data_3", style);


        // 白色背景标题 9号黑字 水平 垂直居中
        Font dataFont3 = wb.createFont();
        dataFont3.setFontHeightInPoints((short) 9);
        style = createBorderedStyle(wb);
        // 设置单元格内容垂直居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style.setFont(dataFont3);
        // 设置单元格内容水平剧中
        style.setAlignment(CellStyle.ALIGN_CENTER);
        styles.put("data_4", style);


        // 白色背景标题 9号黑字 水平 垂直居中 货币格式
        Font dataFont4 = wb.createFont();
        dataFont4.setFontHeightInPoints((short) 9);
        style = createBorderedStyle(wb);
        // 设置单元格内容垂直居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style.setFont(dataFont4);
        // 设置单元格内容水平局右
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        XSSFDataFormat fmt = wb.createDataFormat();
        style.setDataFormat(fmt.getFormat("#,##0.00"));
        styles.put("data_5", style);

        //白色背景标题 14号粗体白字 蓝色背景 水平 垂直剧中 蓝色
        Font headerFont7 = wb.createFont();
        headerFont7.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont7.setFontHeightInPoints((short) 14);
        headerFont7.setColor(IndexedColors.WHITE.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(headerFont7);
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND );
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        styles.put("data_6", style);

        //白色背景标题 14号粗体白字 蓝色背景 水平 垂直剧中 蓝色
        Font headerFont8 = wb.createFont();
        headerFont8.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont8.setFontHeightInPoints((short) 14);
        headerFont8.setColor(IndexedColors.WHITE.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(headerFont8);
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND );
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setDataFormat(fmt.getFormat("#,##0.00"));
        styles.put("data_7", style);

        //白色背景标题 14号粗体白字 蓝色背景 水平 垂直剧中 蓝色
        Font headerFont9 = wb.createFont();
        headerFont9.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont9.setFontHeightInPoints((short) 14);
        headerFont9.setColor(IndexedColors.WHITE.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(headerFont9);
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND );
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        styles.put("data_8", style);

        // 14号粗体白字 黄色背景 水平 垂直剧中 蓝色
        Font headerFont10 = wb.createFont();
        headerFont10.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont10.setFontHeightInPoints((short) 14);
        headerFont10.setColor(IndexedColors.BLACK.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(headerFont10);
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND );
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        styles.put("data_9", style);

        // 14号粗体白字 黄色背景 居右 垂直剧中 黄色
        Font headerFont11 = wb.createFont();
        headerFont11.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont11.setFontHeightInPoints((short) 14);
        headerFont11.setColor(IndexedColors.BLACK.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(headerFont11);
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND );
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setDataFormat(fmt.getFormat("#,##0.00"));
        styles.put("data_10", style);

        // 14号粗体白字 黄色背景 居右 垂直剧中 黄色
        Font headerFont12 = wb.createFont();
        headerFont12.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont12.setFontHeightInPoints((short) 14);
        headerFont12.setColor(IndexedColors.BLACK.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(headerFont12);
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND );
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        styles.put("data_11", style);


        // 白色背景标题 10号黑字 水平 垂直居中
        Font dataFont13 = wb.createFont();
        dataFont13.setBoldweight(Font.BOLDWEIGHT_BOLD);
        dataFont13.setFontHeightInPoints((short) 10);
        style = createBorderedStyle(wb);
        // 设置单元格内容垂直居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style.setFont(dataFont13);
        // 设置单元格内容水平剧中
        style.setAlignment(CellStyle.ALIGN_CENTER);
        styles.put("data_12", style);

        return styles;
    }


}
