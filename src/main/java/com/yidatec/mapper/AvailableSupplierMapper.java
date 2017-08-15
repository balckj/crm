package com.yidatec.mapper;


import com.yidatec.model.Param;
import com.yidatec.vo.AvailableSupplierVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;


/**
 * 可用供应商列表Mapper
 */
@Mapper
public interface AvailableSupplierMapper {

    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "selectAvailableSupplierList")
    List<AvailableSupplierVO> selectAvailableSupplierList(AvailableSupplierVO availableSupplierVO);

    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "countSelectAvailableSupplierList")
    int countSelectAvailableSupplierList(AvailableSupplierVO availableSupplierVO);
}
