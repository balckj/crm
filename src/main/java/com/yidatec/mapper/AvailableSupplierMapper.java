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

    // 未选择类型
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "selectAvailableSupplierList")
    List<AvailableSupplierVO> selectAvailableSupplierList(AvailableSupplierVO availableSupplierVO);
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "countSelectAvailableSupplierList")
    int countSelectAvailableSupplierList(AvailableSupplierVO availableSupplierVO);

    // 工厂
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "selectAvailableSupplierListF")
    List<AvailableSupplierVO> selectAvailableSupplierListF(AvailableSupplierVO availableSupplierVO);
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "countSelectAvailableSupplierListF")
    int countSelectAvailableSupplierListF(AvailableSupplierVO availableSupplierVO);

    // 设计师
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "selectAvailableSupplierListD")
    List<AvailableSupplierVO> selectAvailableSupplierListD(AvailableSupplierVO availableSupplierVO);
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "countSelectAvailableSupplierListD")
    int countSelectAvailableSupplierListD(AvailableSupplierVO availableSupplierVO);


    // 项目经理
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "selectAvailableSupplierListP")
    List<AvailableSupplierVO> selectAvailableSupplierListP(AvailableSupplierVO availableSupplierVO);
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "countSelectAvailableSupplierListP")
    int countSelectAvailableSupplierListP(AvailableSupplierVO availableSupplierVO);

    // 销售
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "selectAvailableSupplierListDS")
    List<AvailableSupplierVO> selectAvailableSupplierListDS(AvailableSupplierVO availableSupplierVO);
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "countSelectAvailableSupplierListDS")
    int countSelectAvailableSupplierListDS(AvailableSupplierVO availableSupplierVO);


    // 跟进销售
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "selectAvailableSupplierListFS")
    List<AvailableSupplierVO> selectAvailableSupplierListFS(AvailableSupplierVO availableSupplierVO);
    @SelectProvider(type=AvailableSupplierQueryProvider.class,method = "countSelectAvailableSupplierListFS")
    int countSelectAvailableSupplierListFS(AvailableSupplierVO availableSupplierVO);
}
