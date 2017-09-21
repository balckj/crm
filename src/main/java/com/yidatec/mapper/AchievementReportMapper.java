package com.yidatec.mapper;


import com.yidatec.vo.AchievementReportVO;
import com.yidatec.vo.LedgerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by jrw on 2017/7/21.
 */
@Mapper
public interface AchievementReportMapper {

    @SelectProvider(type=AchievementReportQueryProvider.class,method = "selectAchievementReportVOList")
    List<AchievementReportVO> selectAchievementReportVOList(String starTime,String endTime);

    @Select("SELECT * FROM T_CONTRACT_HISTORY order by id, modifyTime ASC")
    List<LedgerVO> getLedgerList(String contractId);

}
