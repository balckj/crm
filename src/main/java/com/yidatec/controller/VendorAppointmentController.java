package com.yidatec.controller;

import com.yidatec.model.VendorAppointment;
import com.yidatec.service.VendorAppointmentService;
import com.yidatec.vo.VendorAppointmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by jrw on 2017/7/11.
 */
@Controller
public class VendorAppointmentController extends BaseController{

    @Autowired
    VendorAppointmentService vendorAppointmentService;

    @RequestMapping("/vendorAppointmentList")
    public String vendorAppointmentList(ModelMap model){
        return "vendorAppointmentEdit";
    }


    @RequestMapping("/saveVendorAppointment")
    @ResponseBody
    public Object saveVendorAppointment(VendorAppointment vendorAppointment,
                            BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        vendorAppointment.setCreatorId(getWebUser().getId());
        vendorAppointment.setCreateTime(LocalDateTime.now());
        vendorAppointment.setModifierId(getWebUser().getId());
        vendorAppointment.setModifyTime(LocalDateTime.now());
        vendorAppointmentService.createVendorAppointment(vendorAppointment);
        return getSuccessJson(null);
    }

    @RequestMapping("/deleteVendorAppointment")
    @ResponseBody
    public Object deleteVendorAppointment(String id)throws Exception{
        vendorAppointmentService.deleteVendorAppointment(id);
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findVendorAppointment")
    @ResponseBody
    public List<VendorAppointment> findVendorAppointment(VendorAppointmentVO vendorAppointmentVO)throws Exception{
        List<VendorAppointment> vendorAppointmentList = vendorAppointmentService.selectVendorAppointmentList(vendorAppointmentVO);
        if(vendorAppointmentList != null){

            LocalDateTime systime = LocalDateTime.now();
            Long sysTime = LocalDateTime.of(systime.getYear(),systime.getMonth(),systime.getDayOfMonth(),systime.getHour(),systime.getMinute()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

            for(VendorAppointment su : vendorAppointmentList){
                LocalDateTime startTime = su.getStart();
                Long dBStartTime = LocalDateTime.of(startTime.getYear(),startTime.getMonth(),startTime.getDayOfMonth(),startTime.getHour(),startTime.getMinute()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

                if(startTime.isBefore(systime)){
                    su.setEditable(false);
                    su.setDurationEditable(false);
                }else{
                    su.setEditable(true);
                    su.setDurationEditable(true);
                }
            }
        }
        return vendorAppointmentList;
    }
}
