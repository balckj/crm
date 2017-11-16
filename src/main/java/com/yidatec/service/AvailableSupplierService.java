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

    // 工厂
    public List<AvailableSupplierVO> selectAvailableSupplierListF(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.selectAvailableSupplierListF(availableSupplierVO);
    }

    public int countSelectAvailableSupplierListF(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.countSelectAvailableSupplierListF(availableSupplierVO);
    }

    // 设计师
    public List<AvailableSupplierVO> selectAvailableSupplierListD(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.selectAvailableSupplierListD(availableSupplierVO);
    }

    public int countSelectAvailableSupplierListD(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.countSelectAvailableSupplierListD(availableSupplierVO);
    }

    // 项目经理
    public List<AvailableSupplierVO> selectAvailableSupplierListP(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.selectAvailableSupplierListP(availableSupplierVO);
    }

    public int countSelectAvailableSupplierListP(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.countSelectAvailableSupplierListP(availableSupplierVO);
    }

    // 开发销售
    public List<AvailableSupplierVO> selectAvailableSupplierListDS(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.selectAvailableSupplierListDS(availableSupplierVO);
    }

    public int countSelectAvailableSupplierListDS(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.countSelectAvailableSupplierListDS(availableSupplierVO);
    }

    // 跟进销售
    public List<AvailableSupplierVO> selectAvailableSupplierListFS(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.selectAvailableSupplierListFS(availableSupplierVO);
    }

    public int countSelectAvailableSupplierListFS(AvailableSupplierVO availableSupplierVO, List<Param> paramList){
        availableSupplierVO.setParamList(paramList);
        return availableSupplierMapper.countSelectAvailableSupplierListFS(availableSupplierVO);
    }

}
