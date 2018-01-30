package com.yidatec.service;

import com.yidatec.mapper.ContactMapper;
import com.yidatec.mapper.CustomerMapper;
import com.yidatec.vo.CustomerNewFollowVO;
import com.yidatec.vo.ProjectVO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2017/7/19.
 */

@Service("CustomerYearService")
public class CustomerNewFollowService {
    @Autowired
    CustomerMapper customerMapper;

    public void customerNewFollowDownLoad(XSSFWorkbook wb,
                                 String year) throws Exception {
        List<CustomerNewFollowVO>  customerNewVOList=  customerMapper.customerNewDownLoad(year);

        HashMap<String,CustomerNewFollowVO> nMap = new HashMap<String,CustomerNewFollowVO>();
        Map<String,String> userMap =  new HashMap<String,String>();
        for (CustomerNewFollowVO cnfVO : customerNewVOList) {
            nMap.put(cnfVO.getId()+cnfVO.getYm(),cnfVO);
            userMap.put(cnfVO.getId(),cnfVO.getName());
        }

        List<CustomerNewFollowVO>  customerNewFollowVOList=  customerMapper.customeFollowDownLoad(year);
        HashMap<String,CustomerNewFollowVO> fMap = new HashMap<String,CustomerNewFollowVO>();
        for (CustomerNewFollowVO cnfVO : customerNewFollowVOList) {
            fMap.put(cnfVO.getId()+cnfVO.getYm(),cnfVO);
            userMap.put(cnfVO.getId(),cnfVO.getName());
        }

        XSSFSheet sheet = wb.createSheet("sheet1");
        Map<String, XSSFCellStyle> mapStyle = createStyles(wb);
        sheet.setDisplayGridlines(false);

        // 起始行号
        XSSFRow row0,row1,row = null;
        // 设定标题行号，生成标题
        int rowNum = 0;
        row0 = sheet.createRow(rowNum++);// 第一行
        row0.createCell(0).setCellValue("创建人");
        row0.getCell(0).setCellStyle(mapStyle.get("header_6"));

        row0.createCell(1).setCellValue("客户总数");
        row0.getCell(1).setCellStyle(mapStyle.get("header_6"));

        row1 = sheet.createRow(rowNum++);// 第二行
        row1.createCell(0);
        row1.getCell(0).setCellStyle(mapStyle.get("header_6"));
        row1.createCell(1);
        row1.getCell(1).setCellStyle(mapStyle.get("header_6"));

        CellRangeAddress region = new CellRangeAddress(0,1,0,0);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0,1,1,1);
        sheet.addMergedRegion(region);

        // 打印标题
        for (int i = 2,j=1; i < 26; i++) {
            XSSFCell cell0 = row0.createCell(i);
            XSSFCell cel11 = row1.createCell(i);
            sheet.setColumnWidth(i, 32 * 100);
            cell0.setCellStyle(mapStyle.get("header_6"));
            cel11.setCellStyle(mapStyle.get("header_6"));

            if (i % 2 == 0){
                cell0.setCellValue(j+"月");
                cel11.setCellValue("新建客户数");
                j++;
            }

            if (i % 2 == 1){
                region = new CellRangeAddress(0,0,i-1,i);
                sheet.addMergedRegion(region);
                cel11.setCellValue("跟进客户数");
            }
        }

        // 写入主数据
        /*设置数据起始行号，填充数据*/

        for (String userId :userMap.keySet()){
            String name = userMap.get(userId);
            row = sheet.createRow(rowNum++);
            XSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(name);
            cell0.setCellStyle(mapStyle.get("header_6"));

            XSSFCell cell1 = row.createCell(1);
            cell1.setCellStyle(mapStyle.get("header_6"));
            int customerCout = 0;
            for(int m = 1,n=2; m <= 12;m++){
                XSSFCell newCustomerCell = row.createCell(n++);
                newCustomerCell.setCellStyle(mapStyle.get("header_6"));

                XSSFCell followCustomerCell = row.createCell(n++);
                followCustomerCell.setCellStyle(mapStyle.get("header_6"));

                String ym = m == 10 ? year+ "-" + m : (m < 10 ? year+ "-" + "0" + m : year+ "-" + m);
                String id = userId+ ym;

                //  新建客户
                CustomerNewFollowVO customerNewVO = nMap.get(id);
                if(customerNewVO != null){
                    customerCout = customerCout + Integer.parseInt(customerNewVO.getCount());
                    newCustomerCell.setCellValue(customerNewVO.getCount());
                }
                //  跟进客户
                CustomerNewFollowVO customerFollowVO = fMap.get(id);
                if(customerFollowVO != null){
                    customerCout = customerCout + Integer.parseInt(customerFollowVO.getFollowCount());
                    followCustomerCell.setCellValue(customerFollowVO.getFollowCount());
                }

            }
            cell1.setCellValue(customerCout);
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


        //白色背景标题 9号粗体黑字
        Font headerFont4 = wb.createFont();
        headerFont4.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont4.setFontHeightInPoints((short) 10);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(headerFont4);
        styles.put("header_6", style);


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

        return styles;
    }
}
