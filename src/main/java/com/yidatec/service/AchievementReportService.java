package com.yidatec.service;

import com.yidatec.mapper.AchievementReportMapper;
import com.yidatec.mapper.ProductMapper;
import com.yidatec.mapper.ProjectMapper;
import com.yidatec.mapper.QuotationMapper;
import com.yidatec.model.Product;
import com.yidatec.util.CNNumberFormat;
import com.yidatec.vo.AchievementReportVO;
import com.yidatec.vo.QuotationVO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Service("achievementReportService")
public class AchievementReportService {

	@Autowired
	AchievementReportMapper achievementReportMapper;

	public void downLoad(XSSFWorkbook wb,
								  String starTime,String endTime) throws Exception {

		HashMap<String,List<AchievementReportVO>> map = new HashMap<String,List<AchievementReportVO>>();

		List<AchievementReportVO> achievementReportVOList =  achievementReportMapper.selectAchievementReportVOList(null,null);

		List<String> dicIdList =  new ArrayList<String>();

		for (AchievementReportVO achievementReportVO : achievementReportVOList) {

			if(!dicIdList.contains(achievementReportVO.getProjectId())){
				dicIdList.add(achievementReportVO.getProjectId());
			}

			List<AchievementReportVO> achievementReportVOList1 = map.get(achievementReportVO.getProjectId());

			if (achievementReportVOList1 == null){
				achievementReportVOList1 = new ArrayList<>();
				map.put(achievementReportVO.getProjectId(),achievementReportVOList1);
			}

			achievementReportVOList1.add(achievementReportVO);
		}

		XSSFSheet sheet = wb.createSheet("sheet1");
		Map<String, XSSFCellStyle> mapStyle = createStyles(wb);
		sheet.setDisplayGridlines(false);

		// 起始行号
		XSSFRow row = null;
		// 设定标题行号，生成标题
		int rowNum = 0;
		row = sheet.createRow(rowNum);

		row.createCell(0).setCellValue("类别");
		sheet.setColumnWidth(0, 32 * 80);
		row.createCell(1).setCellValue("编号");
		sheet.setColumnWidth(1, 32 * 50);
		row.createCell(2).setCellValue("产品名称");
		sheet.setColumnWidth(2, 32 * 150);
		row.createCell(3).setCellValue("单位");
		sheet.setColumnWidth(3, 32 * 50);
		row.createCell(4).setCellValue("数量");
		sheet.setColumnWidth(4, 32 * 50);
		row.createCell(5).setCellValue("单价(元)");
		sheet.setColumnWidth(5, 32 * 80);
		row.createCell(6).setCellValue("合价(元)");
		sheet.setColumnWidth(6, 32 * 150);
		row.createCell(7).setCellValue("工作内容");
		sheet.setColumnWidth(7, 32 * 500);

		// 第2行以上冻结
		sheet.createFreezePane(0, 2, 0, 2);

		row.getCell(0).setCellStyle(mapStyle.get("header_7"));
		row.getCell(1).setCellStyle(mapStyle.get("header_7"));
		row.getCell(2).setCellStyle(mapStyle.get("header_7"));
		row.getCell(3).setCellStyle(mapStyle.get("header_7"));
		row.getCell(4).setCellStyle(mapStyle.get("header_7"));
		row.getCell(5).setCellStyle(mapStyle.get("header_7"));
		row.getCell(6).setCellStyle(mapStyle.get("header_7"));
		row.getCell(7).setCellStyle(mapStyle.get("header_7"));
		row.setHeightInPoints(30);

		int num = 1;

		Double countPrict = new Double(0);

        /*设置数据起始行号，填充数据*/
//		for (String s : dicIdList) {
//			List<QuotationVO> quotationVOList2 = map.get(s);
//			for (int i = 0; i< quotationVOList2.size() ; i ++) {
//				QuotationVO quotationVO = quotationVOList2.get(i);
//				row = sheet.createRow(++rowNum);
//				// 类别
//				row.createCell(0);
//
//				row.getCell(0).setCellValue(quotationVO.getCategoryName());
//
//				row.setHeightInPoints(20);
//			}
//		}


	}

	private void setColspanTitle(XSSFSheet sheet,XSSFRow row,
								 Map<String, XSSFCellStyle> mapStyle,
								 XSSFWorkbook wb,
								 String title,
								 String cellStyle,
								 int cell,
								 int StringRow,
								 int endRow,
								 int StringCell,
								 int endCell){
		row.createCell(cell).setCellValue(title);
		// 参数：起始行号，终止行号， 起始列号，终止列号
		CellRangeAddress region = new CellRangeAddress(StringRow,endRow,StringCell,endCell);
		sheet.addMergedRegion(region);
		row.getCell(cell).setCellStyle(mapStyle.get(cellStyle));//"data_4"
		int border = 2;
		RegionUtil.setBorderBottom(border,region, sheet, wb);
		RegionUtil.setBorderLeft(border,region, sheet, wb);
		RegionUtil.setBorderRight(border,region, sheet, wb);
		RegionUtil.setBorderTop(border,region, sheet, wb);
		row.setHeightInPoints(30);
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
