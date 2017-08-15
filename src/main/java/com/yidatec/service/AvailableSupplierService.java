package com.yidatec.service;

import com.yidatec.mapper.AvailableSupplierMapper;
import com.yidatec.model.AvailableSupplier;
import com.yidatec.model.Param;
import com.yidatec.vo.AvailableSupplierVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service("availableSupplierService")
public class AvailableSupplierService {

    @Autowired
    AvailableSupplierMapper availableSupplierMapper;
    @Autowired
    ParamService paramService;


    public List<AvailableSupplierVO> selectAvailableSupplierList(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.selectAvailableSupplierList(availableSupplierVO);
    }

    public int countSelectAvailableSupplierList(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.countSelectAvailableSupplierList(availableSupplierVO);
    }
}
