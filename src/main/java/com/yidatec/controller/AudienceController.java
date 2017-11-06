package com.yidatec.controller;

import com.yidatec.exception.BusinessException;
import com.yidatec.exception.ExceptionID;
import com.yidatec.mapper.UserMapper;
import com.yidatec.model.Activity;
import com.yidatec.model.Audience;
import com.yidatec.model.Dictionary;
import com.yidatec.model.User;
import com.yidatec.service.ActivityService;
import com.yidatec.service.AudienceService;
import com.yidatec.service.DictionaryService;
import com.yidatec.util.*;
import com.yidatec.vo.AudienceVO;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static com.yidatec.util.FileUtills.buildFile;

/**
 * Created by jrw on 2017/7/12.
 * 观众Controller
 */
@Controller
public class AudienceController extends BaseController {

    @Autowired
    AudienceService audienceService;

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    ActivityService activityService;

    @Autowired
    ConfProperties confProperties;

    @Autowired
    UserMapper userMapper;

    //默认单元格内容为数字时格式
    private  DecimalFormat df = new DecimalFormat("0");
    // 默认单元格格式化日期字符串
    private  SimpleDateFormat sdf = new SimpleDateFormat(  "yyyy-MM-dd HH:mm:ss");
    // 格式化数字
    private  DecimalFormat nf = new DecimalFormat("0");

    @RequestMapping("/audienceList")
    public String audienceList() {
        return "audienceList";
    }

    @RequestMapping("/audienceEdit")
    public String audienceEdit(ModelMap model, @RequestParam(value = "id", required = false) String id) {
        model.put("title", (id == null || id.isEmpty()) ? "新建观众" : "编辑观众");
        model.put("audience", audienceService.selectAudience(id));
        model.put("hobbyList", dictionaryService.selectDictionaryListByCodeCommon(Constants.HOBBY));
        model.put("campaignList", activityService.activityList());
        return "audienceEdit";
    }

    @RequestMapping("/saveAudience")
    @ResponseBody
    public Object saveAudience(@Validated @RequestBody Audience audience,
                               BindingResult result) throws Exception {
        List<FieldError> errors = result.getFieldErrors();
        if (errors != null && errors.size() > 0) {
            return errors;
        }
        if (audience.getId() == null || audience.getId().trim().length() <= 0) {//新建
            audience.setId(UUID.randomUUID().toString().toLowerCase());
            audience.setCreatorId(getWebUser().getId());
            audience.setCreateTime(LocalDateTime.now());
            audience.setModifierId(getWebUser().getId());
            audience.setModifyTime(audience.getCreateTime());
            audienceService.createAudience(audience);
        } else {
            audience.setModifierId(getWebUser().getId());
            audience.setModifyTime(LocalDateTime.now());
            audienceService.updateAudience(audience);
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findAudience")
    @ResponseBody
    public Object findAudience(@RequestBody AudienceVO audienceVO) throws Exception {
        List<Audience> audienceList = audienceService.selectAudienceList(audienceVO);
//        if(audienceList != null && audienceList.size() > 0){
//            for (Audience audience : audienceList){
//                String campaignId = audience.getCampaignId();
//                if(!StringUtils.isEmpty(campaignId)){
//                    String[] campaignIds = campaignId.split(",");
//                    String temp = "";
//                    for(int i = 0 ; i < campaignIds.length; i++){
//                        Activity activity = activityService.selectActivity(campaignIds[i]);
//                        if(i != campaignIds.length -1){
//                            temp = temp + activity.getName()  +",";
//                        }else{
//                            temp = temp + activity.getName();
//                        }
//                    }
//                    audience.setCampaignId(temp);// 活动名称
//                }
//            }
//        }
        int count = audienceService.countSelectAudienceList(audienceVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", audienceVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", audienceList);
        return map;
    }

    @RequestMapping(value = "audienceTempleteDownload")
    public void audienceTempleteDownload (HttpServletRequest request, HttpServletResponse response)throws Exception{
        String fileName = "观众模板.xlsx";
        String showName = "观众模板.xlsx";
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setContentType("application/x-msdownload");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + URLEncoder.encode(showName, "UTF-8"));
            // 模板路径
            File cfgFile =  ResourceUtils.getFile("classpath:\\static\\template");
            String filePath = cfgFile+File.separator;
            String downLoadPath = filePath + fileName;
            File downLoadFile = new File(downLoadPath);
            byte[] res = FileUtils.readFileToByteArray(downLoadFile);
            out.write(res);
        } catch (Exception e) {
            throw new BusinessException(ExceptionID.DOWNLOAD_FILE_ERROR,e);
        } finally {
            if(out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (Exception ee) {
                }
            }
        }
    }

    @RequestMapping(value = "errorFileDownload")
    public void errorFileDownload (HttpServletRequest request, HttpServletResponse response,@RequestParam(value="errorFileName",required = false) String errorFileName)throws Exception{
        String showName = "观众模板.xlsx";
        OutputStream out = null;
        File downLoadFile = null;
        try {
            out = response.getOutputStream();
            response.setContentType("application/x-msdownload");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + URLEncoder.encode(showName, "UTF-8"));
            String filePath = buildFile(confProperties.getUpLoadAudieceFileError(),true)+File.separator;
            String downLoadPath = filePath + errorFileName;
            downLoadFile = new File(downLoadPath);
            byte[] res = FileUtils.readFileToByteArray(downLoadFile);
            out.write(res);
        } catch (Exception e) {
            throw new BusinessException(ExceptionID.DOWNLOAD_FILE_ERROR,e);
        } finally {
            if(out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (Exception ee) {
                }
            }
            // 下载后删除错误文件
            delFile(downLoadFile);
        }
    }

    @RequestMapping(value = "/audienceileupload", method = RequestMethod.POST)
    @ResponseBody
    public Map audienceileupload(@RequestParam("files") MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Audience> res = readExcel(file.getInputStream(),file.getName());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("res", res != null && res.size() > 0 ? 1 : 0);
        map.put("errorFileName", file.getName());
        return map;
    }

    private List<Audience> readExcel(InputStream is,String fileName) {
        List<Audience> audiencesList = new ArrayList<Audience>();
        try {
            XSSFWorkbook wb = new XSSFWorkbook(is);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;
            Map<String, XSSFCellStyle> mapStyle = createStyles(wb);
            //获取Sheet表中所包含的总行数
            int rsRows = sheet.getPhysicalNumberOfRows();
            boolean errorFlg = false;

            for (int i = 0; i < rsRows; i++){ // 忽略标题
                if (i == 0 ) continue;
                row = sheet.getRow(i);
                if (checkCellCalue(mapStyle,row, row.getCell(0),0)
                        || checkCellCalue(mapStyle,row, row.getCell(1),1)
                        || checkCellCalue(mapStyle,row, row.getCell(2),2)
                        || checkCellCalue(mapStyle,row, row.getCell(3),3)
                        || checkCellCalue(mapStyle,row, row.getCell(4),4)
                        || checkCellCalue(mapStyle,row, row.getCell(5),5)
                        || checkCellCalue(mapStyle,row, row.getCell(6),6)
                        || checkCellCalue(mapStyle,row, row.getCell(7),7)){
                    errorFlg = true;
                    checkCellCalue(mapStyle,row, row.getCell(0),0);
                    checkCellCalue(mapStyle,row, row.getCell(1),1);
                    checkCellCalue(mapStyle,row, row.getCell(2),2);
                    checkCellCalue(mapStyle,row, row.getCell(3),3);
                    checkCellCalue(mapStyle,row, row.getCell(4),4);
                    checkCellCalue(mapStyle,row, row.getCell(5),5);
                    checkCellCalue(mapStyle,row, row.getCell(6),6);
                    checkCellCalue(mapStyle,row, row.getCell(7),7);
                }
            }

            if(errorFlg){
                // 有错误把错误文件写入磁盘
                try {
                    errorReportWrite(wb,fileName);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                List<Dictionary> hobbyDicList = dictionaryService.selectDictionaryListByCodeCommon(Constants.HOBBY);
                for (int i = 0; i < rsRows; i++){ // 忽略标题
                    if (i == 0 ) continue;
                    row = sheet.getRow(i);
                    Audience audience = new Audience();
                    audience.setId(UUID.randomUUID().toString());
                    audience.setName(getCellValue(row.getCell(0)));
                    audience.setMobilePhone(getCellValue(row.getCell(1)));
                    audience.setEmail(getCellValue(row.getCell(2)));
                    audience.setCountry(getCellValue(row.getCell(3)));
                    audience.setProvince(getCellValue(row.getCell(4)));
                    audience.setCity(getCellValue(row.getCell(5)));
                    audience.setRegion(getCellValue(row.getCell(6)));

                    String[] hobby = getCellValue(row.getCell(7)).split(",");
                    String hobbyId = "";
                    for (String str :hobby ){
                        for (Dictionary dictionary : hobbyDicList){
                            if (StringUtils.endsWithIgnoreCase(str,dictionary.getValue())){
                                hobbyId += dictionary.getId() + ",";
                            }
                        }
                    }
                    if (hobbyId.indexOf(",") > -1){
                        hobbyId = hobbyId.substring(0,hobbyId.lastIndexOf(","));
                    }
                    audience.setHobby(hobbyId);
                    audience.setCreatorId(getWebUser().getId());
                    audience.setCreateTime(LocalDateTime.now());
                    audience.setModifierId(getWebUser().getId());
                    audience.setModifyTime(audience.getCreateTime());
                    audiencesList.add(audience);
                }
                // 插入数据库
                audienceService.createAudienceList(audiencesList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return audiencesList;
    }

    private String getCellValue(XSSFCell cell){
        Object value  = new Object();
        if(cell == null ||  cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
            value = "";
        }else if(cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
            value = cell.getStringCellValue();
        }else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
            if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                value = df.format(cell.getNumericCellValue());
            } else if ("General".equals(cell.getCellStyle()
                    .getDataFormatString())) {
                value = nf.format(cell.getNumericCellValue());
            } else {
                value = sdf.format(HSSFDateUtil.getJavaDate(cell
                        .getNumericCellValue()));
            }
        }else if(cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN){
            value = Boolean.valueOf(cell.getBooleanCellValue());
        }else if(cell.getCellType() == XSSFCell.CELL_TYPE_BLANK){
            value = "";
        }else{
            value = "";
        }

        return value.toString();
    }

    private boolean checkCellCalue( Map<String, XSSFCellStyle> mapStyle, XSSFRow row,XSSFCell cell,int cellNumb){
        if(StringUtils.isEmpty(getCellValue(cell))){
            row.createCell(cellNumb).setCellValue("");
            row.getCell(cellNumb).setCellStyle(mapStyle.get("data_1"));
            return  true;
        } if(!StringUtils.isEmpty(getCellValue(cell)) && cellNumb == 1){
            String mobile = getCellValue(cell);
            User user = userMapper.findByMobilePhone(mobile);
            if(user != null){
                row.getCell(cellNumb).setCellStyle(mapStyle.get("data_1"));
                return  true;
            }else {
                return false;
            }
        } else {
            return  false;
        }
    }

    private void errorReportWrite(XSSFWorkbook wb,String fileName) throws Exception{
        String path = buildFile(confProperties.getUpLoadAudieceFileError(),true) + File.separator +fileName;
        File outFile = new File(path);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outFile);
            wb.write(outputStream);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }finally {
            if(outputStream != null)
                outputStream.close();
        }
    }

    private Map<String, XSSFCellStyle> createStyles(XSSFWorkbook wb) {
        Map<String, XSSFCellStyle> styles = new HashMap<String, XSSFCellStyle>();
        //白色背景标题 11号粗体红字字 居左
        XSSFCellStyle style;
        //白色背景标题 14号粗体白字 玫瑰红色背景 水平 垂直剧中 蓝色
        Font headerFont7 = wb.createFont();
//        headerFont7.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont7.setFontHeightInPoints((short) 11);
        headerFont7.setColor(IndexedColors.BLACK.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(headerFont7);
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND );
        style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
        styles.put("data_1", style);

        return styles;
    }

    private XSSFCellStyle createBorderedStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        return style;
    }

    private void delFile(File outFile){
        if (outFile.exists() && outFile.isFile()) {
            if (outFile.delete()) {
                System.out.println("删除单个文件" + outFile.getName() + "成功！");
            } else {
                System.out.println("删除单个文件" + outFile.getName() + "失败！");
            }
        }
    }
}
