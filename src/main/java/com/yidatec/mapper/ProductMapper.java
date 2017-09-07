package com.yidatec.mapper;


import com.yidatec.model.Product;
import com.yidatec.vo.ProductVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * jrw
 */
@Mapper
public interface ProductMapper {

	/**
	 * 查找一个产品
	 * @return
	 */
	@Select("SELECT * FROM T_PRODUCTION WHERE id = #{id}")
	Product selectProduct(String id);

	@Select("SELECT * FROM T_PRODUCTION WHERE id = #{id}")
	Product selectProductDetail(String id);


	@SelectProvider(type=ProductQueryProvider.class,method = "selectProductList")
	List<Product> selectProductList(ProductVO product);
	@SelectProvider(type=ProductQueryProvider.class,method = "countSelectProductList")
	int countSelectProductList(ProductVO product);

	@Insert("INSERT INTO T_PRODUCTION (id,`category`,`name`,`desc`,unit,way,low,middle,high," +
			"creatorId,createTime,modifierId,modifyTime) VALUES (" +
			"#{id},#{category},#{name},#{desc},#{unit},#{way},#{low},#{middle},#{high}," +
			"#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int create(Product product);

	@Update("UPDATE T_PRODUCTION SET `category`=#{category},`name`=#{name},`desc`=#{desc},unit=#{unit},way=#{way},low=#{low},middle=#{middle},high=#{high}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(Product product);

	@Delete("DELETE FROM T_PRODUCTION WHERE id=#{id}")
	int delete(String id);

}
