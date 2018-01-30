package com.yidatec.controller;

import com.yidatec.service.CustomerService;
import com.yidatec.service.CustomerNewFollowService;
import com.yidatec.service.OderTrackingReportService;
import com.yidatec.service.ReportService;
import com.yidatec.util.DownloadHelper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jrw on 2017/7/13.
 */
@Controller
public class ReportController extends BaseController{

    @Autowired
    ReportService reportService;

    @Autowired
    OderTrackingReportService oderTrackingReportService;

    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerNewFollowService customerYearService;

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
        oderTrackingReportService.generateOderTrackingReport(wb,beginYear,entYear);

        new DownloadHelper().downLoad(wb, response, fileName);
    }

    @RequestMapping(value = "/customerDownLoad")
    public void customerDownLoad (
            HttpServletRequest request, HttpServletResponse response,
            @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime3,
            @DateTimeFormat(pattern="yyyy-MM-dd") Date endTime3
    ) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String beginYear = sdf.format(startTime3);
        String entYear = sdf.format(endTime3);
        String date = sdf.format(new Date());
        String fileName = "客户关系大表"+date+".xlsx";
        XSSFWorkbook wb = new XSSFWorkbook();

        customerService.customerDownLoad(wb,beginYear,entYear);

        new DownloadHelper().downLoad(wb, response, fileName);
    }

    @RequestMapping(value = "/customerNewFollowDownLoad")
    public void customerNewFollowDownLoad (
            HttpServletRequest request, HttpServletResponse response,
            @DateTimeFormat(pattern="yyyy") Date year
    ) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String beginYear = sdf.format(year);
        String date = sdf.format(new Date());
        String fileName = "客户"+date+".xlsx";
        XSSFWorkbook wb = new XSSFWorkbook();
        customerYearService.customerNewFollowDownLoad(wb,beginYear);
        new DownloadHelper().downLoad(wb, response, fileName);
    }
}
