package com.yidatec.mapper;


import com.yidatec.model.Exhibition;
import com.yidatec.model.Product;
import com.yidatec.vo.ExhibitionVO;
import com.yidatec.vo.ProductVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * jrw
 */
@Mapper
public interface ExhibitionMapper {

	/**
	 * 查找一个展馆
	 * @return
	 */
	@Select("SELECT id,name,address,start as startTime,country,province,city,region,area,createTime,creatorId,modifyTime,modifierId FROM T_EXHIBITION_HALL WHERE id = #{id}")
	ExhibitionVO selectExhibition(String id);


	/**
	 * 查找一个展馆ALL
	 * @return
	 */
	@Select("SELECT id,name,address,start as startTime,country,province,city,region,area,createTime,creatorId,modifyTime,modifierId FROM T_EXHIBITION_HALL")
	List<ExhibitionVO> selectExhibitionAll();

	@SelectProvider(type=ExhibitionQueryProvider.class,method = "selectExhibitionList")
	List<ExhibitionVO> selectExhibitionList(ExhibitionVO exhibitionVO);
	@SelectProvider(type=ExhibitionQueryProvider.class,method = "countSelectExhibitionList")
	int countSelectExhibitionList(ExhibitionVO exhibitionVO);

	@Insert("INSERT INTO T_EXHIBITION_HALL (id,`name`,`address`,country,province,city,region,area," +
			"creatorId,createTime,modifierId,modifyTime) VALUES (" +
			"#{id},#{name},#{address},#{country},#{province},#{city},#{region},#{area}," +
			"#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int create(Exhibition exhibition);

	@Update("UPDATE T_EXHIBITION_HALL SET `name`=#{name},address=#{address},country=#{country},province=#{province},city=#{city},region=#{region},area=#{area}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(Exhibition exhibition);

	@Delete("DELETE FROM T_EXHIBITION_HALL WHERE id=#{id}")
	int delete(String id);

}
