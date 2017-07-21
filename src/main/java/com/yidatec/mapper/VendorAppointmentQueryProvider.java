package com.yidatec.mapper;

import com.yidatec.vo.VendorAppointmentVO;
import org.springframework.util.StringUtils;

/**
 * Created by jrw on 17-7-18.
 */
public class VendorAppointmentQueryProvider {

    public String selectVendorAppointmentList(final VendorAppointmentVO vendorAppointmentVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM T_VENDOR_APPOINTMENT  as D WHERE 1=1");

        if(!StringUtils.isEmpty(vendorAppointmentVO.getVendorId())){
            sb.append(" AND D.vendorId = #{vendorId}");
        }
        sb.append(" AND D.start between #{vendorId}  and #{vendorId}");
        return sb.toString();
    }
}
