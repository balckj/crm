package com.yidatec.service;

import com.yidatec.mapper.ReportMapper;
import com.yidatec.mapper.UserMapper;
import com.yidatec.model.Dictionary;
import com.yidatec.util.Constants;
import com.yidatec.vo.DesignerReportVO;
import com.yidatec.vo.FactoryReportVO;
import com.yidatec.vo.LedgerReportVO;
import com.yidatec.vo.PerformanceReportVO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    static String yingshouCostId = "d0bddb87-009e-491a-a034-35726e020a64";//应收
    static String yingfuCostId = "02f3bfc8-2e8c-412e-aaeb-f368561aea76";//应付
    static List<String> cKeyList =new ArrayList<String>(5);
    static{
        cKeyList.add(dianxiangCostId);
        cKeyList.add(manageCostId);
        cKeyList.add(shentuCostId);
        cKeyList.add(baoxianCostId);
        cKeyList.add(saleCostId);
        cKeyList.add(yingshouCostId);
        cKeyList.add(yingfuCostId);
    }

    public void generateOderTrackingReport(XSSFWorkbook wb, String starTime,String endTime) throws Exception {
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

        // 台账数据处理，合同到台账的映射
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

        row1.createCell(colIndex).setCellValue("开展日期");
        sheet.setColumnWidth(colIndex++, 32 * 260);

        row1.createCell(colIndex).setCellValue("市场活动名称");
        sheet.setColumnWidth(colIndex++, 32 * 100);

        row1.createCell(colIndex).setCellValue("面积/㎡");
        sheet.setColumnWidth(colIndex++, 32 * 80);

        row1.createCell(colIndex).setCellValue("展位号");
        sheet.setColumnWidth(colIndex++, 32 * 50);

        row1.createCell(colIndex).setCellValue("活动类型");
        sheet.setColumnWidth(colIndex++, 32 * 80);

        row1.createCell(colIndex).setCellValue("项目名称");
        sheet.setColumnWidth(colIndex++, 32 * 120);

        row1.createCell(colIndex).setCellValue("成本中心");
        sheet.setColumnWidth(colIndex++, 32 * 80);

        row1.createCell(colIndex).setCellValue("合同总价");
        sheet.setColumnWidth(colIndex++, 32 * 80);

        row1.createCell(colIndex).setCellValue("客户增减费用");
        sheet.setColumnWidth(colIndex++, 32 * 150);

        row1.createCell(colIndex).setCellValue("代缴费用");
        sheet.setColumnWidth(colIndex++, 32 * 80);

        row1.createCell(colIndex).setCellValue("客户总金额");
        sheet.setColumnWidth(colIndex++, 32 * 120);

//        // 客户
//        for(int i = 0;i < 5 ;i++){
//            row1.createCell(colIndex).setCellValue("应收");
//            sheet.setColumnWidth(colIndex++, 32 * 50);
//        }

//        row1.createCell(colIndex).setCellValue("应收帐款");
//        sheet.setColumnWidth(colIndex++, 32 * 80);
//
//        row1.createCell(colIndex).setCellValue("供应商-名称");
//        sheet.setColumnWidth(colIndex++, 32 * 80);
//
//
//        row1.createCell(colIndex).setCellValue("供应商-合同编码");
//        sheet.setColumnWidth(colIndex++, 32 * 80);
//
//
//        row1.createCell(colIndex).setCellValue("供应商-合同价 ");
//        sheet.setColumnWidth(colIndex++, 32 * 80);
//
//        // 供应商
//        for(int i = 0;i < 5 ;i++){
//            row1.createCell(colIndex).setCellValue("应付");
//            sheet.setColumnWidth(colIndex++, 32 * 50);
//        }
//
//        row1.createCell(colIndex).setCellValue("应收账款");
//        sheet.setColumnWidth(colIndex++, 32 * 50);
//
        row1.setHeightInPoints(30);
//
        for(int i = 0 ; i < colIndex ; i++){
            row1.getCell(i).setCellStyle(mapStyle.get("header_6"));
        }

        for(String projectId : projectIdList){
            Map<String, List<PerformanceReportVO>> casMap = map.get(projectId);//当项目没有合同时，casMap.size()为0
            List<PerformanceReportVO> saleContractList = casMap.get("C");
            int saleContractCount = saleContractList == null ? 0:saleContractList.size();

            if(saleContractList != null){
                for (PerformanceReportVO one : saleContractList){
                    colIndex = 0;
                    XSSFRow row = null;
                    row = sheet.getRow(rowNum);
                    if(row == null) {
                        row = sheet.createRow(rowNum);

                        row.createCell(colIndex++).setCellValue(one.getContractCode()); // 客户合同号
                        row.createCell(colIndex++).setCellValue(one.getCampaignStartEndTime()); // 市场活动日期
                        row.createCell(colIndex++).setCellValue(one.getCampaignName()); // 市场活动名称
                        row.createCell(colIndex++).setCellValue(one.getContractArea()); // 面积
                        row.createCell(colIndex++).setCellValue(one.getProjectName()); //

                        row.createCell(colIndex++).setCellValue(one.getCampaignType() == null ? "" : dictionaryService.selectDictionary(one.getCampaignType()).getValue());

                        row.createCell(colIndex++).setCellValue(one.getExhibitionNumber());
                        row.createCell(colIndex++).setCellValue(one.getContractCountAmount());
                        row.createCell(colIndex++).setCellValue(one.getContractCountAmountChange());
                    }
                }
            }


        }

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
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(headerFont4);
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
