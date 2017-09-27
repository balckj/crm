package com.yidatec.controller;

import com.yidatec.service.*;
import com.yidatec.util.DownloadHelper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jrw on 2017/7/13.
 */
@Controller
public class ReportController extends BaseController{

    @Autowired
    ReportService reportService;

    @RequestMapping("/downLoadIndex")
    public  String downLoadIndex(){
        return  "downLoad";
    }

    @RequestMapping(value = "performanceDownLoad")
    public void performanceReportDownLoad (
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
        reportService.generatePerformanceReport(wb,beginYear,entYear);

        new DownloadHelper().downLoad(wb, response, fileName);
    }

    @RequestMapping(value = "orderTrackingDownLoad")
    public void orderTrackingDownLoad (
            HttpServletRequest request, HttpServletResponse response,
            @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime2,
            @DateTimeFormat(pattern="yyyy-MM-dd") Date endTime2
    ) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        String fileName = "订单追踪"+date+".xlsx";
        XSSFWorkbook wb = new XSSFWorkbook();

        String beginYear = sdf.format(startTime2);
        String entYear = sdf.format(endTime2);
        reportService.generateOderTrackingReport(wb,beginYear,entYear);

        new DownloadHelper().downLoad(wb, response, fileName);
    }
}
