package com.yidatec.controller;

import com.yidatec.model.Dictionary;
import com.yidatec.model.Product;
import com.yidatec.service.*;
import com.yidatec.util.DownloadHelper;
import com.yidatec.vo.ProductVO;
import com.yidatec.vo.QuotationVO;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by jrw on 2017/7/13.
 */
@Controller
public class ReportController extends BaseController{

    @Autowired
    AchievementReportService achievementReportService;

    @RequestMapping("/downLoadIndex")
    public  String downLoadIndex(){
        return  "downLoad";
    }

    @RequestMapping(value = "achievementReportDownLoad")
    public void achievementReportDownLoad (
            HttpServletRequest request, HttpServletResponse response,
            @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime,
            @DateTimeFormat(pattern="yyyy-MM-dd") Date endTime
            ) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        String fileName = "业绩管理"+date+".xlsx";
        XSSFWorkbook wb = new XSSFWorkbook();

        String beginYear = sdf.format(startTime);
        String entYear = sdf.format(endTime);
        achievementReportService.downLoad(wb,beginYear,entYear);

        new DownloadHelper().downLoad(wb, response, fileName);
    }
}
